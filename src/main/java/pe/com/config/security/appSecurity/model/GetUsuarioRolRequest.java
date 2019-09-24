package pe.com.config.security.appSecurity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetUsuarioRolRequest {

	private String codUsuario;
	private String loginUsuario;
	private Integer codRol;
	private String feIniVige;
	private String feFinVige;
	private int stUsuaRol;
	private int stRegi;
	
	public GetUsuarioRolRequest() {}
	
}
