package pe.com.config.security.appSecurity.jwt.config;

import java.util.Optional;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public class RefreshToken implements JwtToken {

	private Jws<Claims> claims;

	private RefreshToken(Jws<Claims> claims) {
		this.claims = claims;
	}

	/**
	 * Creates and validates Refresh token
	 *
	 * @param token
	 * @param signingKey
	 *
	 * @throws BadCredentialsException
	 * @throws JwtExpiredTokenException
	 *
	 * @return
	 */
	public static Optional<RefreshToken> create(RawAccessJwtToken token, String signingKey) {
		Jws<Claims> claims = token.parseClaims(signingKey);
		return Optional.of(new RefreshToken(claims));
	}

	@Override
	public String getToken() {
		return null;
	}

	public Jws<Claims> getClaims() {
		return claims;
	}

	public String getJti() {
		return claims.getBody().getId();
	}

	public String getSubject() {
		return claims.getBody().getSubject();
	}

}
