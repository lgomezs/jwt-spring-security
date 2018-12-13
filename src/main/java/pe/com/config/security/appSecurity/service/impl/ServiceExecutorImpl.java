package pe.com.config.security.appSecurity.service.impl;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import pe.com.config.security.appSecurity.repository.base.Command;
import pe.com.config.security.appSecurity.repository.base.ServiceExecutor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component
public class ServiceExecutorImpl implements ServiceExecutor,ApplicationContextAware{

	 	private ApplicationContext applicationContext;

	    @Override
	    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
	        this.applicationContext = applicationContext;
	    }
	    
	    
	    @Override
	    public <T, R> Mono<T> execute(Class<? extends Command<T, R>> commandClass, R request) {
	        Command<T, R> command = applicationContext.getBean(commandClass);
	        return command.execute(request);
	    }

	    @Override
	    public <T, R> Flux<T> executeList(Class<? extends Command<T, R>> commandClass, R request) {
	        Command<T, R> command = applicationContext.getBean(commandClass);
	        Flux<T> data = command.executeList(request);
	        return data;
	    }


		@Override
		public <T, R> Mono<Void> delete(Class<? extends Command<T, R>> commandClass, R request) {
			Command<T, R> command = applicationContext.getBean(commandClass);
			return command.delete(request);
		}
			
}
