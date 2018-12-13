package pe.com.config.security.appSecurity.exception;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class CommandValidationException extends RuntimeException {

    private Set<ConstraintViolation<?>> constrainValidation;

    public CommandValidationException(Set constrainValidation) {
        this.constrainValidation = (Set<ConstraintViolation<?>>) constrainValidation;
    }

    public Set<ConstraintViolation<?>> getConstrainValidation() {
        return constrainValidation;
    }
}
