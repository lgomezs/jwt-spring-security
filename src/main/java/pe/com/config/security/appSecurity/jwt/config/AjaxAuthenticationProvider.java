package pe.com.config.security.appSecurity.jwt.config;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import pe.com.config.security.appSecurity.bean.Rol;
import pe.com.config.security.appSecurity.bean.Usuario;
import pe.com.config.security.appSecurity.bean.UsuarioRol;
import pe.com.config.security.appSecurity.model.UserContext;
import pe.com.config.security.appSecurity.service.RolService;
import pe.com.config.security.appSecurity.service.UsuarioRolService;
import pe.com.config.security.appSecurity.service.UsuarioService;
import reactor.core.publisher.Flux;

@Component
public class AjaxAuthenticationProvider implements AuthenticationProvider{
	private final BCryptPasswordEncoder encoder;   
    private final UsuarioRolService usuarioRolService;
    private final UsuarioService usuarioService;
    private final RolService rolsService;

    @Value("${spring.security.ldap.active}")
    private boolean ldapActive;

   // @Autowired
   // private LdapService ldapService;
    
    
    @Autowired
    public AjaxAuthenticationProvider(final  UsuarioService usuarioService,final BCryptPasswordEncoder encoder,
    				final UsuarioRolService usuarioRolService,
    				final RolService rolsService) {       
        this.encoder = encoder;
        this.usuarioRolService=usuarioRolService;
        this.usuarioService=usuarioService;
        this.rolsService=rolsService;
    }
    
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.notNull(authentication, "Datos de autenticaci\u00f3n no proveidos");
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        Usuario user = null;

        //verifica si esta activo el LDAP
        if(ldapActive){
//            if(!ldapService.authenticate(username, password)){              
//            	throw new BadCredentialsException("Usuario o contrase\u00f1a no v\u00e1lidos.");
//            }
        }

        try {          	
        	user = usuarioService.findByEmail(username).blockOptional().orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
        }catch (Exception exception) {        	
				throw new AuthenticationServiceException("Error en validar usuario", exception);
		}        
              
        if(!ldapActive && !(user.getStUsua()==1)){
        	throw new BadCredentialsException("Usuario inactivo.");
        }      
        
        if(!user.isGoogle()) {
        	//se valida el password
        	if (!ldapActive && !encoder.matches(password, user.getPassword())) {
                throw new BadCredentialsException("Usuario o contrase\u00f1a no v\u00e1lidos.");
        	}        	
        }else {
        	//validate with api google
        	
        }                     
        
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
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

}

