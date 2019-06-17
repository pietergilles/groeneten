package be.vdab.groenetenen.messaging;

import be.vdab.groenetenen.domain.Offerte;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class OfferteEnOffertesURL {
    private Offerte offerte;
    private String offertesURL;
// je maakt getters en een geparametriseerde constructor

    public OfferteEnOffertesURL(Offerte offerte, String offertesURL)
    {
        this.offerte = offerte;
        this.offertesURL = offertesURL;
    }

    public Offerte getOfferte()
    {
        return offerte;
    }

    public String getOffertesURL()
    {
        return offertesURL;
    }
    // je maakt ook een protected default constructor die JAXB nodig heeft

    protected OfferteEnOffertesURL()
    {
    }
}