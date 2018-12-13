package pe.com.config.security.appSecurity.exception;

import javax.validation.ConstraintViolation;
import javax.validation.Path;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import pe.com.config.security.appSecurity.model.ResponseModel;


@RestControllerAdvice
public class ExceptionController {

	 	@ExceptionHandler(Throwable.class)
	    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	    public ResponseModel<Object> handleThrowable(Throwable throwable) {
	        return ResponseModel.status(HttpStatus.INTERNAL_SERVER_ERROR, throwable.getMessage());
	    }

	    @ExceptionHandler(CommandValidationException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    public ResponseModel<Object> handleCommandValidationException(CommandValidationException e) {
	        ResponseModel<Object> response = ResponseModel.status(HttpStatus.BAD_REQUEST, null);

	        e.getConstrainValidation().forEach(violation -> {
	            for (String attribute : getAttributes(violation)) {
	                response.addErrors(attribute, violation.getMessage());
	            }
	        });

	        return response;
	    }

	    private String[] getAttributes(ConstraintViolation<?> constraintViolation) {
	        String[] values = (String[]) constraintViolation.getConstraintDescriptor().getAttributes().get("path");
	        if (values == null || values.length == 0) {
	            return getAttributesFromPath(constraintViolation);
	        } else {
	            return values;
	        }
	    }

	    private String[] getAttributesFromPath(ConstraintViolation<?> constraintViolation) {
	        Path path = constraintViolation.getPropertyPath();
	        StringBuilder builder = new StringBuilder();
	        path.forEach(node -> {
	            if (node.getName() != null) {
	                if (builder.length() > 0) {
	                    builder.append(".");
	                }

	                builder.append(node.getName());
	            }
	        });

	        return new String[]{builder.toString()};
	    }

	    
}
