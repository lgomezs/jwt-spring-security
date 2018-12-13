package pe.com.config.security.appSecurity.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import pe.com.config.security.appSecurity.bean.Rol;
import reactor.core.publisher.Mono;

@Repository
public interface ReactiveRespositoryRol extends ReactiveMongoRepository<Rol, String> {

	public Mono<Rol> findByRolCodAndStRol(int rolcod,int staRol);
	
	public Mono<Rol> findById(String id);
}
