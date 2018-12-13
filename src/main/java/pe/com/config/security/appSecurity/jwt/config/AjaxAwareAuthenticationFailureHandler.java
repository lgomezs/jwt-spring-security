package pe.com.config.security.appSecurity.jwt.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import pe.com.config.security.appSecurity.common.ErrorCode;
import pe.com.config.security.appSecurity.exception.AuthMethodNotSupportedException;
import pe.com.config.security.appSecurity.exception.ErrorResponse;
import pe.com.config.security.appSecurity.exception.JwtExpiredTokenException;





@Component
public class AjaxAwareAuthenticationFailureHandler implements AuthenticationFailureHandler {

	private final ObjectMapper mapper;

	@Autowired
	public AjaxAwareAuthenticationFailureHandler(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException e) throws IOException, ServletException {

		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);

		if (e instanceof BadCredentialsException || e instanceof AuthMethodNotSupportedException) {
			mapper.writeValue(response.getWriter(),
					ErrorResponse.of(e.getMessage(), ErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED));
		} else if (e instanceof JwtExpiredTokenException) {
			mapper.writeValue(response.getWriter(),
					ErrorResponse.of("Token ha expirado", ErrorCode.JWT_TOKEN_EXPIRED, HttpStatus.UNAUTHORIZED));
		}
		mapper.writeValue(response.getWriter(),
				ErrorResponse.of("Fallo la autenticaci\u00f3n : " + e.getMessage(), ErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED));
	}

}
