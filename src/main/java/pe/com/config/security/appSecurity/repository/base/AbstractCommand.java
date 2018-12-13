package pe.com.config.security.appSecurity.repository.base;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import pe.com.config.security.appSecurity.exception.BaseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract class AbstractCommand<T, ID> implements Command<T, ID>,ApplicationContextAware,InitializingBean{

	protected Validator validator;
    protected ApplicationContext applicationContext;
    public abstract Mono<T> doExecute(ID request);
    public abstract Flux<T> doExecuteList(ID request);
    public abstract Mono<Void> doDelete(ID request);
     
    @Override
    public final void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public final void afterPropertiesSet() throws Exception {
        this.validator = applicationContext.getBean(Validator.class);
    }
  
    @Override
    public Mono<T> execute(ID request) {
        Set<ConstraintViolation<ID>> constraintViolations = validator.validate(request);
        if (constraintViolations.isEmpty()) {
            return doExecute(request);
        } else {
            return Mono.error(new BaseException(constraintViolations));
        }
    }

    @Override
    public Flux<T> executeList(ID request) {
        Set<ConstraintViolation<ID>> constraintViolations = validator.validate(request);
        if (constraintViolations.isEmpty()) {
            return doExecuteList(request);
        } else {
            return Flux.error(new BaseException(constraintViolations));
        }
    }
    
	@Override
	public Mono<Void> delete(ID request) {
		Set<ConstraintViolation<ID>> constraintViolations = validator.validate(request);
        if (constraintViolations.isEmpty()) {
            return doDelete(request);
        } else {
            return Mono.error(new BaseException(constraintViolations));
        }
	}
		
}
