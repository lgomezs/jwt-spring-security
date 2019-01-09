package pe.com.config.security.appSecurity;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import pe.com.config.security.appSecurity.bean.Usuario;
import pe.com.config.security.appSecurity.bean.UsuarioRol;
import pe.com.config.security.appSecurity.model.GetUsuarioRequest;
import pe.com.config.security.appSecurity.service.UsuarioRolService;
import pe.com.config.security.appSecurity.service.UsuarioService;
import reactor.core.publisher.Flux;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AppSecurityApplicationTests {

	@Autowired
	private UsuarioService _usuarioService;
	@Autowired
	private UsuarioRolService usuarioRolService;
	
		
	@Test
	@Ignore
	public void listUsuarios() {
		GetUsuarioRequest _gGetUsuarioRequest= new GetUsuarioRequest();	
		List<Usuario> listUssuario = _usuarioService.executeList(_gGetUsuarioRequest)		 
		 .filter(filtro-> filtro.getEmail().equals("lgome@gmail.com"))
		 .collect(Collectors.toList()).block();
		
		listUssuario.stream()
		.forEach(System.out::println);
				
		List<Usuario> lista = _usuarioService.executeList(_gGetUsuarioRequest)		 
				 .filter(filtro-> filtro.getStUsua()>0)
				 .collect(Collectors.toList()).block();
						
		lista.stream()
		.forEach(System.out::println);
		
	}
	
	
	@Test
	public void listUsuarioRol() {
		GetUsuarioRequest _gGetUsuarioRequest= new GetUsuarioRequest();	
		List<Flux<UsuarioRol>> listaUsuarioRol=	 _usuarioService.executeList(_gGetUsuarioRequest).toStream()
				.filter(p	->  p.getStUsua() > 0)
				.map(userRol -> usuarioRolService.findByUsuarioCodAndStUsuaRol(userRol.getCodUsuario(), 1))
				.collect(Collectors.toList());
			
		System.out.println(listaUsuarioRol.size());
		
		System.out.println(" ******************  Roles ***************************");
		listaUsuarioRol.forEach(usuarioRol-> {	
			usuarioRol.collectList().block().forEach(p -> {
				System.out.println(p.getId() + " " + p.getCodUsuario() + " " +  p.getRolCod());				
			});
			
//			System.out.println(usuarioRol.blockFirst().getId() + " " + usuarioRol.blockFirst().getCodUsuario() + " "+ 
//						usuarioRol.blockFirst().getRolCod());
		});
		System.out.println(" ****************** End  Roles ***************************");
		
	} 
	
	

}
