package pe.com.config.security.appSecurity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.config.security.appSecurity.bean.UsuarioRol;
import pe.com.config.security.appSecurity.model.GetUsuarioRolRequest;
import pe.com.config.security.appSecurity.repository.ReactiveRespositoryUsuarioRol;
import pe.com.config.security.appSecurity.repository.base.AbstractCommand;
import pe.com.config.security.appSecurity.service.UsuarioRolService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class UsuarioRolServiceImpl extends AbstractCommand<UsuarioRol, GetUsuarioRolRequest> implements UsuarioRolService{
	
	@Autowired
	private ReactiveRespositoryUsuarioRol reactiveRespositoryUsuarioRol;

	
	@Override
	public Mono<UsuarioRol> doExecute(GetUsuarioRolRequest request) {
		return reactiveRespositoryUsuarioRol.insert(createUsuarioRol(request));
	}

	@Override
	public Flux<UsuarioRol> doExecuteList(GetUsuarioRolRequest request) {
		return reactiveRespositoryUsuarioRol.findAll();
	}

	@Override
	public Mono<Void> doDelete(GetUsuarioRolRequest request) {
		return null;
	}

	@Override
	public Flux<UsuarioRol> findByUsuarioCodAndStUsuaRol(String codUsuario, int stUsuaRol) {		
		return reactiveRespositoryUsuarioRol.findByCodUsuarioAndStUsuaRol(codUsuario,stUsuaRol);
	}

	public UsuarioRol createUsuarioRol(GetUsuarioRolRequest request){
      return UsuarioRol.builder()
        .codUsuario(request.getCodUsuario())
        .rolCod(request.getCodRol())
        .stUsuaRol(request.getStUsuaRol())
        .stRegi(request.getStRegi())
        .feIniVige(request.getFeIniVige())
        .feFinVige(request.getFeFinVige())
      .build();

  }

}
