package pe.com.config.security.appSecurity.service;

import pe.com.config.security.appSecurity.bean.Rol;
import pe.com.config.security.appSecurity.model.GetRolRequest;
import pe.com.config.security.appSecurity.repository.base.Command;
import reactor.core.publisher.Mono;

public interface RolService extends Command<Rol, GetRolRequest>{

	public Mono<Rol> findByRolCodAndStRol(int rolcod,int staRol);
	
	public Mono<Rol> findById(String id);
}
