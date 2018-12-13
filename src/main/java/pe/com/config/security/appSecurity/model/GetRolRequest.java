package pe.com.config.security.appSecurity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetRolRequest {
	private String id;
	private String codigo;
	private Integer rolCodigo;
	private String nombreRol;
	private String descripcionRol;
	private String tipoRol;
	private int estadoRol;
	private int estadoRegi;
	
}
