package pe.com.config.security.appSecurity.model;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import pe.com.config.security.appSecurity.bean.Usuario;

public class UserContext {	
	private Usuario usuario;
	private String userId;
	private final List<GrantedAuthority> authorities;

	private UserContext(String userId, List<GrantedAuthority> authorities,Usuario usuario) {
		this.userId = userId;
		this.authorities = authorities;
		this.usuario=usuario;
	}

	public static UserContext create(Usuario usuario, List<GrantedAuthority> authorities) {
		if (usuario.getId() == null)
			throw new IllegalArgumentException("Usuario es requerido");		
		return new UserContext(usuario.getId(), authorities,usuario);
	}
	
	public List<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getUserId() {
		return userId;
	}
	

}
