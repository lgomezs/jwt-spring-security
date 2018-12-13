package pe.com.config.security.appSecurity.jwt.config;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import pe.com.config.security.appSecurity.bean.Rol;
import pe.com.config.security.appSecurity.bean.Usuario;
import pe.com.config.security.appSecurity.bean.UsuarioRol;
import pe.com.config.security.appSecurity.model.UserContext;
import pe.com.config.security.appSecurity.service.RolService;
import pe.com.config.security.appSecurity.service.UsuarioRolService;
import pe.com.config.security.appSecurity.service.UsuarioService;
import reactor.core.publisher.Flux;



@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

	private final JwtSettings jwtSettings;	 
	@Autowired
    private UsuarioService usuarioService;
	@Autowired
	private UsuarioRolService usuarioRolService;
	@Autowired
	private RolService rolsService;
    
    @Autowired
    public JwtAuthenticationProvider(JwtSettings jwtSettings) {
        this.jwtSettings = jwtSettings;
    }
    
    @Cacheable
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        RawAccessJwtToken rawAccessToken = (RawAccessJwtToken) authentication.getCredentials();

        Jws<Claims> jwsClaims = rawAccessToken.parseClaims(jwtSettings.getTokenSigningKey());
              
        Usuario user = usuarioService.findById(jwsClaims.getBody().getSubject()).blockOptional().orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + jwsClaims));
        
        //se reuperan los roles
        Flux<UsuarioRol>  listtUsuarioRol= usuarioRolService.findByUsuarioCodAndStUsuaRol(user.getCodUsuario(), user.getStUsua());
                          
        if(listtUsuarioRol!=null && listtUsuarioRol.count().block()>0) {	
        	List<Rol> listRol=new ArrayList<Rol>();  
        	
        	for(UsuarioRol _usuarioRol:listtUsuarioRol.collectList().block()) {   
        		listRol.add(rolsService.findByRolCodAndStRol(_usuarioRol.getRolCod(), _usuarioRol.getStRegi()).block());
        	}
        	 
        	List<GrantedAuthority> authorities = listRol.stream()
                    .map(authority -> new SimpleGrantedAuthority(authority.getNoRol()))
                    .collect(Collectors.toList());        	
      
            UserContext userContext = UserContext.create(user, authorities);            
            
            return new UsernamePasswordAuthenticationToken(userContext, null, userContext.getAuthorities());
                       
        }else {
        	throw new InsufficientAuthenticationException("Usuario no tiene sistemas asignados");
        }          
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
