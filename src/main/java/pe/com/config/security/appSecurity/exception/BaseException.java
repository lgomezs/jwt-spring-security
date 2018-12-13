package pe.com.config.security.appSecurity.exception;

import java.util.Set;

import javax.validation.ConstraintViolation;

public class BaseException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Set<ConstraintViolation<?>> constrainValidation;

    public BaseException(Set constrainValidation) {
        this.constrainValidation = (Set<ConstraintViolation<?>>) constrainValidation;
    }

    public Set<ConstraintViolation<?>> getConstrainValidation() {
        return constrainValidation;
    }
    
}
