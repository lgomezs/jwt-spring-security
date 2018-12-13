package pe.com.config.security.appSecurity.service;

import pe.com.config.security.appSecurity.bean.UsuarioRol;
import pe.com.config.security.appSecurity.model.GetUsuarioRolRequest;
import pe.com.config.security.appSecurity.repository.base.Command;
import reactor.core.publisher.Flux;

public interface UsuarioRolService extends Command<UsuarioRol, GetUsuarioRolRequest>{

	public Flux<UsuarioRol> findByUsuarioCodAndStUsuaRol(String codUsuario,int stUsuaRol);
}
