package pe.com.config.security.appSecurity.bean;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Document(collection="usuarios")
public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private String codUsuario;
	private String nombre;
	private String email;
	private String password;
	private String role;
	private String img;
	private boolean google;
	private int stUsua;
	private List<SimpleGrantedAuthority> authorities;

	public Usuario() {}
	
	public Usuario(String email, String password, List<SimpleGrantedAuthority> authorities) {
		
	}
	
	
}
