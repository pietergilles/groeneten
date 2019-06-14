package be.vdab.groenetenen.RestServices;

import be.vdab.groenetenen.Exceptions.FiliaalHeeftNogWerknemersException;
import be.vdab.groenetenen.Exceptions.FiliaalNietGevondenException;
import be.vdab.groenetenen.domain.Filiaal;
import be.vdab.groenetenen.services.FiliaalService;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/filialen")
@ExposesResourceFor(Filiaal.class)
class FiliaalRestController {
    private final EntityLinks entityLinks;
    private final FiliaalService filiaalService;
    FiliaalRestController(FiliaalService filiaalService, EntityLinks entityLinks) {
        this.filiaalService = filiaalService;
        this.entityLinks = entityLinks;
    }
    @GetMapping("{filiaal}")
    FiliaalResource get(@PathVariable Optional<Filiaal> filiaal) {
        if (filiaal.isPresent()) {
            return new FiliaalResource(filiaal.get(), entityLinks);
        }
        throw new FiliaalNietGevondenException();
    }
    @ExceptionHandler(FiliaalNietGevondenException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void filiaalNietGevonden() {
    }
    @DeleteMapping("{filiaal}")
    void delete(@PathVariable Optional<Filiaal> filiaal) {
        if (! filiaal.isPresent()) {
            throw new FiliaalNietGevondenException();
        }
        filiaalService.delete(filiaal.get());
    }
    @ExceptionHandler(FiliaalHeeftNogWerknemersException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String filiaalHeeftNogWerknemers() {
        return "filiaal heeft nog werknemers";
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    HttpHeaders create(@RequestBody @Valid Filiaal filiaal) {
        filiaalService.create(filiaal);
        HttpHeaders headers = new HttpHeaders();
        Link link =
                entityLinks.linkToSingleResource(Filiaal.class, filiaal.getId());
        headers.setLocation(URI.create(link.getHref()));
        return headers;
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String filiaalMetVerkeerdeProperties(MethodArgumentNotValidException ex) {
        StringBuilder fouten = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                fouten.append(error.getField()).append(':')
                .append(error.getDefaultMessage()).append('\n'));
        fouten.deleteCharAt(fouten.length() - 1);
        return fouten.toString();
    }
    @PutMapping("{id}")
    void update(@RequestBody @Valid Filiaal filiaal) {
        filiaalService.update(filiaal);
    }
    @GetMapping
    FilialenResource findAll() {
        return new FilialenResource(filiaalService.findAll(), entityLinks);
    }

}
