package pe.com.config.security.appSecurity.repository.base;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ServiceExecutor {

	<T, R> Mono<T> execute(Class<? extends Command<T, R>> commandClass, R request);

    <T, R> Flux<T> executeList(Class<? extends Command<T, R>> commandClass, R request);   
    
    <T, R> Mono<Void> delete(Class<? extends Command<T, R>> commandClass, R request);
 
}
