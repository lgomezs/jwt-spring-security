package pe.com.config.security.appSecurity.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Document(collection="UsuarioRol")
public class UsuarioRol {

	@Id
	private String id;
	@Field("codUsuario")
	private String codUsuario;
	private int rolCod;	
	private String feIniVige;	
	private String feFinVige;
	private int stUsuaRol;
	private int stRegi;
	
			
}
