package pe.com.config.security.appSecurity.service;

import pe.com.config.security.appSecurity.bean.Usuario;
import pe.com.config.security.appSecurity.model.GetUsuarioRequest;
import pe.com.config.security.appSecurity.repository.base.Command;
import reactor.core.publisher.Mono;

public interface UsuarioService extends Command<Usuario, GetUsuarioRequest>{

	Mono<Usuario> findByEmailAndPassword(String login,String password);
	
	Mono<Usuario> findByEmail(String login);
	
	Mono<Usuario> updateUsuario(GetUsuarioRequest request);
		
	Mono<Usuario> login(GetUsuarioRequest request);
	
	Mono<Usuario> findById(String id);
}
