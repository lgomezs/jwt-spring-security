package pe.com.config.security.appSecurity.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import pe.com.config.security.appSecurity.bean.UsuarioRol;
import reactor.core.publisher.Flux;

@Repository
public interface ReactiveRespositoryUsuarioRol extends ReactiveMongoRepository<UsuarioRol, String> {

	public Flux<UsuarioRol> findByCodUsuarioAndStUsuaRol(String codUsuario,int stUsuaRol);
	
}
