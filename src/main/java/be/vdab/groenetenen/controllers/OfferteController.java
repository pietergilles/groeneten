package be.vdab.groenetenen.controllers;

import be.vdab.groenetenen.domain.Offerte;
import be.vdab.groenetenen.services.OfferteService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("offertes")
@SessionAttributes("offerte")
class OfferteController {
    private final OfferteService offerteService;
    OfferteController(OfferteService offerteService) {
        this.offerteService = offerteService;
    }
    @GetMapping("toevoegen")
    ModelAndView stap1() {
        return new ModelAndView("offerteStap1").addObject(new Offerte());
    }
    @InitBinder("offerte")
    void initBinder(DataBinder binder) {
        binder.initDirectFieldAccess();
    }

    @PostMapping(value = "toevoegen", params = "stap2")
    String naarStap2(@Validated(Offerte.Stap1.class) Offerte offerte,
                     Errors errors) {
        return errors.hasErrors() ? "offerteStap1": "offerteStap2";
    }
    @PostMapping(value = "toevoegen", params = "stap1")
    String naarStap1(Offerte offerte) {
        return "offerteStap1";
    }
    @PostMapping(value = "toevoegen", params = "opslaan")
    String create(@Validated(Offerte.Stap2.class) Offerte offerte, Errors errors,
                  SessionStatus session, HttpServletRequest request) {
        if (errors.hasErrors()) {
            return "offerteStap2";
        }
        String offertesURL = request.getRequestURL().toString().replace("toevoegen","");
        offerteService.create(offerte, offertesURL);
        session.setComplete();
        return "redirect:/";
    }
    @GetMapping("{optionalOfferte}")
    ModelAndView read(@PathVariable Optional<Offerte> optionalOfferte) {
        ModelAndView modelAndView = new ModelAndView("offerte");
        optionalOfferte.ifPresent(
                offerte -> modelAndView.addObject("offer", offerte));
        return modelAndView;
    }
}