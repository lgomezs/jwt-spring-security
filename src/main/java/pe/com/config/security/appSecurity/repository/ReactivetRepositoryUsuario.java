package pe.com.config.security.appSecurity.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import pe.com.config.security.appSecurity.bean.Usuario;
import reactor.core.publisher.Mono;

@Repository
public interface ReactivetRepositoryUsuario extends ReactiveMongoRepository<Usuario, String>{

	Mono<Usuario> findByEmailAndPassword(String login,String password);
	
	Mono<Usuario> findByEmail(String login);	
	
	Mono<Usuario> findById(String id);
}
