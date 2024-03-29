package be.vdab.groenetenen.restclients;

import be.vdab.groenetenen.Exceptions.KanKoersNietLezenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;

@Component
class FixerKoersClient implements KoersClient {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final URI fixerURL;
    private final RestTemplate restTemplate;
    FixerKoersClient(@Value("${fixerKoersURL}") URI fixerURL,
                     RestTemplateBuilder restTemplateBuilder) {
        this.fixerURL = fixerURL;
        this.restTemplate = restTemplateBuilder.build();
    }
    @Override
    public BigDecimal getDollarKoers() {
        try {
            USDRate rate = restTemplate.getForObject(fixerURL, USDRate.class);
            return rate.getRates().getUSD();
        } catch (RestClientException ex) {
            logger.error("kan koers niet lezen", ex);
            throw new KanKoersNietLezenException();
        }
    }
}