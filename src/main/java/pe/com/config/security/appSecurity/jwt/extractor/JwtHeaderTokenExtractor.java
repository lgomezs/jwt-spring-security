package pe.com.config.security.appSecurity.jwt.extractor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;

import pe.com.config.security.appSecurity.jwt.config.TokenExtractor;




@Component
public class JwtHeaderTokenExtractor implements TokenExtractor{

	public static String HEADER_PREFIX = "";

    @Override
    public String extract(String header) {
        if (StringUtils.isBlank(header)) {
            throw new AuthenticationServiceException("Authorization header no puede estar vacio!");
        }

        if (header.length() < HEADER_PREFIX.length()) {
            throw new AuthenticationServiceException("Invalido tamano de authorization header.");
        }

        return header.substring(HEADER_PREFIX.length(), header.length());
    }
    
}
