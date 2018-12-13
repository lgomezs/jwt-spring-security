package pe.com.config.security.appSecurity.exception;

import org.springframework.security.core.AuthenticationException;

import pe.com.config.security.appSecurity.jwt.config.JwtToken;


public class JwtExpiredTokenException extends AuthenticationException{

	private static final long serialVersionUID = -5959543783324224864L;

    private JwtToken token;

    public JwtExpiredTokenException(String msg) {
        super(msg);
    }

    public JwtExpiredTokenException(JwtToken token, String msg, Throwable t) {
        super(msg, t);
        this.token = token;
    }

    public String token() {
        return this.token.getToken();
    }
    
}
