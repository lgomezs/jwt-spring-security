package pe.com.config.security.appSecurity.repository.base;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface Command<T, ID> {

	 	  Mono<T> execute(ID request);

	    Flux<T> executeList(ID request);
	    
	    Mono<Void> delete(ID request);    
}
