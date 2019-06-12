package be.vdab.groenetenen.forms;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

// enkele imports
public class VanTotPostcodeForm {
    @NotNull
    @Range(min = 1000, max = 9999)
    private final Integer van;
    @NotNull
    @Range(min = 1000, max = 9999)
    private final Integer tot;
// je maakt een geparametriseerde constructor en getters

    public VanTotPostcodeForm(@NotNull @Range(min = 1000, max = 9999) Integer van, @NotNull @Range(min = 1000, max = 9999) Integer tot) {
        this.van = van;
        this.tot = tot;
    }

    public Integer getVan() {
        return van;
    }

    public Integer getTot() {
        return tot;
    }
}