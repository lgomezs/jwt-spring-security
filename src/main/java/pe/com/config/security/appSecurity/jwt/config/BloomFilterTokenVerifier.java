package pe.com.config.security.appSecurity.jwt.config;

import org.springframework.stereotype.Component;

@Component
public class BloomFilterTokenVerifier implements TokenVerifier{

	@Override
	public boolean verify(String jti) {
		 return true;
	}

}
