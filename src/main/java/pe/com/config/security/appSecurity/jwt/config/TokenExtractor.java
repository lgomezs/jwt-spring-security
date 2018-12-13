package pe.com.config.security.appSecurity.jwt.config;

public interface TokenExtractor {
	 public String extract(String payload);
}
