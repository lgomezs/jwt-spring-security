package pe.com.config.security.appSecurity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.config.security.appSecurity.bean.Rol;
import pe.com.config.security.appSecurity.model.GetRolRequest;
import pe.com.config.security.appSecurity.repository.ReactiveRespositoryRol;
import pe.com.config.security.appSecurity.repository.base.AbstractCommand;
import pe.com.config.security.appSecurity.service.RolService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RolServiceImpl extends AbstractCommand<Rol, GetRolRequest> implements RolService{

	@Autowired
	private ReactiveRespositoryRol reactiveRespositoryRol;
	
	@Override
	public Mono<Rol> findByRolCodAndStRol(int rolcod, int staRol) {		
		return reactiveRespositoryRol.findByRolCodAndStRol(rolcod,staRol);
	}

	@Override
	public Mono<Rol> doExecute(GetRolRequest request) {	
		return reactiveRespositoryRol.insert(createRol(request));
	}

	@Override
	public Flux<Rol> doExecuteList(GetRolRequest request) {		
		return reactiveRespositoryRol.findAll();
	}

	@Override
	public Mono<Void> doDelete(GetRolRequest request) {	
		return reactiveRespositoryRol.delete(findById(request.getId()).block());
	}
	
	
	@Override
	public Mono<Rol> findById(String id) {		
		return reactiveRespositoryRol.findById(id);
	}
	
	public Rol createRol(GetRolRequest request) {
		return Rol.builder()
				.rolCod(request.getRolCodigo())
				.noRol(request.getNombreRol())
				.deRol(request.getDescripcionRol())
				.tiRol(request.getTipoRol())
				.stRol(request.getEstadoRol())
				.stRegi(request.getEstadoRegi())
				.build();
	}


}
