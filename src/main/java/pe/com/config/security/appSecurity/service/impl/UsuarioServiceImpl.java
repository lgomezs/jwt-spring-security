package pe.com.config.security.appSecurity.service.impl;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.config.security.appSecurity.bean.Usuario;
import pe.com.config.security.appSecurity.exception.BaseException;
import pe.com.config.security.appSecurity.model.GetUsuarioRequest;
import pe.com.config.security.appSecurity.repository.ReactivetRepositoryUsuario;
import pe.com.config.security.appSecurity.repository.base.AbstractCommand;
import pe.com.config.security.appSecurity.service.UsuarioService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UsuarioServiceImpl extends AbstractCommand<Usuario, GetUsuarioRequest> implements UsuarioService{

	
	@Autowired
	private ReactivetRepositoryUsuario _reactivetRepositoryUsuario;
	
	@Override
	public Mono<Usuario> findByEmailAndPassword(String login, String password) {		
		return _reactivetRepositoryUsuario.findByEmailAndPassword(login,password);
	}

	@Override
	public Mono<Usuario> doExecute(GetUsuarioRequest request) {
		Usuario _usuario=createUsuario(request);
		return _reactivetRepositoryUsuario.save(_usuario);
	}

	@Override
	public Flux<Usuario> doExecuteList(GetUsuarioRequest request) {	
		return _reactivetRepositoryUsuario.findAll();
	}

	@Override
	public Mono<Void> doDelete(GetUsuarioRequest request) {		
		return _reactivetRepositoryUsuario.delete(findByEmail(request.getEmail()).block());
	}

	@Override
	public Mono<Usuario> findByEmail(String login) {		
		return _reactivetRepositoryUsuario.findByEmail(login);
	}

	@Override
	public Mono<Usuario> updateUsuario(GetUsuarioRequest request) {	
		Set<ConstraintViolation<GetUsuarioRequest>> constraintViolations = validator.validate(request);
        if (constraintViolations.isEmpty() && request.getIdUsuario()!=null) {
        	Usuario _usuarioUpdate = createUsuario(request);
        	Usuario _usuario = _reactivetRepositoryUsuario.findById(request.getIdUsuario()).block();        
        	_usuarioUpdate.setId(_usuario.getId());
        	return _reactivetRepositoryUsuario.save(_usuarioUpdate);
        } else {
            return Mono.error(new BaseException(constraintViolations));
        }      
	}

	@Override
	public Mono<Usuario> login(GetUsuarioRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Usuario createUsuario(GetUsuarioRequest request) {
		return Usuario.builder().
				nombre(request.getNombre()).				
				password(encriptarPassword(request.getPassword())).
				role(request.getRole()).
				img(request.getImg())
				.build();
	}
		
	public String encriptarPassword(String password) {
		return null;
	}

	@Override
	public Mono<Usuario> findById(String id) {		
		return _reactivetRepositoryUsuario.findById(id);
	}

	
}
