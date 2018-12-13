package pe.com.config.security.appSecurity.jwt.config;

public interface TokenVerifier {
	public boolean verify(String jti);
}
