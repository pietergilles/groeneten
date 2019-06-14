package be.vdab.groenetenen.domain;

import be.vdab.groenetenen.constraints.Postcode;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;

@Embeddable
@XmlAccessorType(XmlAccessType.FIELD)
@JsonAutoDetect(fieldVisibility= JsonAutoDetect.Visibility.ANY)
public class Adres implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotBlank
    private String straat;
    @NotBlank
    private String huisNr;
    @NotNull
    @Postcode
    private int postcode;
    @NotBlank
    private String gemeente;
// Je maakt getters voor alle variabelen (behalve serialVersionUID)

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public String getHuisNr() {
        return huisNr;
    }

    public void setHuisNr(String huisNr) {
        this.huisNr = huisNr;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public String getGemeente() {
        return gemeente;
    }

    public void setGemeente(String gemeente) {
        this.gemeente = gemeente;
    }
}