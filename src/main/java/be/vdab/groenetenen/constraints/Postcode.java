package be.vdab.groenetenen.constraints;
import org.hibernate.validator.constraints.Range;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
// enkele andere imports
@Target({FIELD, METHOD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = PostcodeValidator.class)
public @interface Postcode {
    String message() default "{be.vdab.groenetenen.constraints.Postcode.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}