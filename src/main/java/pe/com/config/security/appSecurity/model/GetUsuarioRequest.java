package pe.com.config.security.appSecurity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetUsuarioRequest {

	private String idUsuario;
	private String nombre;
	private String email;
	private String password;
	private String role; 
	private String img;
	
	public GetUsuarioRequest() {}
}
