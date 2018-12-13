package pe.com.config.security.appSecurity.jwt.config;

import java.util.UUID;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import pe.com.config.security.appSecurity.bean.Usuario;
import pe.com.config.security.appSecurity.model.UserContext;
import pe.com.config.security.appSecurity.service.UsuarioService;

@Component
public class JwtTokenFactory {
	private final JwtSettings settings;    
    @Autowired
    private UsuarioService usuarioService;
    
    
    @Autowired
    public JwtTokenFactory(JwtSettings settings) {
        this.settings = settings;
    }
    
    public AccessJwtToken createAccessJwtToken(UserContext userContext) {
        if (userContext.getUsuario().getId()==null)
            throw new IllegalArgumentException("No se puede crear token JWT sin usuario");

        if (userContext.getAuthorities() == null || userContext.getAuthorities().isEmpty())
            throw new IllegalArgumentException("El Usuario no tiene ning\u00fan privilegio");

        Claims claims = Jwts.claims().setSubject(String.valueOf(userContext.getUsuario().getId()));
        
        Usuario usuarioDto = usuarioService.findById(userContext.getUsuario().getId()).block();
        
        DateTime currentTime = new DateTime();
        
        claims.put("cuentaUsuario", usuarioDto.getEmail());
        claims.put("empleado", usuarioDto.getNombre());
        
        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer(settings.getTokenIssuer())
                .setIssuedAt(currentTime.toDate())
                .setExpiration(currentTime.plusMinutes(settings.getTokenExpirationTime()).toDate())
                .signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey())
                .compact();

        return new AccessJwtToken(token, claims);
    }

    public JwtToken createRefreshToken(UserContext userContext) {
        if (userContext.getUsuario().getId()==null) {
            throw new IllegalArgumentException("No se puede crear token JWT sin usuario");
        }
        DateTime currentTime = new DateTime();

        Claims claims = Jwts.claims().setSubject(String.valueOf(userContext.getUsuario().getId()));
       
        Usuario usuarioDto = usuarioService.findById(userContext.getUsuario().getId()).block();
                
        claims.put("cuentaUsuario", usuarioDto.getEmail());
        claims.put("empleado", usuarioDto.getNombre());
        
       
        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer(settings.getTokenIssuer())
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(currentTime.toDate())
                .setExpiration(currentTime.plusMinutes(settings.getRefreshTokenExpTime()).toDate())
                .signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey())
                .compact();

        return new AccessJwtToken(token, claims);
    }    

}
