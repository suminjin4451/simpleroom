package com.suminjin.simpleroom.security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.suminjin.simpleroom.model.User;
import com.suminjin.simpleroom.repository.UserRepository;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class JWTProvider {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserDetailsServiceImpl userDetailService;

	private static long tokenValidMilisecond = 1000L * 60 * 60 * 60;

	private static String secretString = "7tMtkV8YENOMRAHo8pphMl3Y8lmaygoIDb0EI0SlV8Y=";

	private static Key SECRET_KEY = Keys.hmacShaKeyFor(secretString.getBytes());

	public String generateJwt(User user) {
		Date time = new Date();
		Date accessTokenExpiresIn = new Date(time.getTime() + tokenValidMilisecond);

		User matchedUser = userRepository.findByUserID(user.getUserID()).orElse(null);

		String accessToken = Jwts.builder().setSubject(matchedUser.getUserID()).setExpiration(accessTokenExpiresIn)
				.claim("role", matchedUser.getRole().toString()).signWith(SECRET_KEY).compact();

		return accessToken;
	}

	public Authentication getAuthentication(String jwt) {
		UserDetails userdetail = userDetailService.loadUserByUsername(this.getUserIdFromJwt(jwt));
		return new UsernamePasswordAuthenticationToken(userdetail, "", userdetail.getAuthorities());

	}

	public boolean validateJwt(String jwt) {
		try {
			Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(jwt);
			return true;
		} catch (JwtException e) {
			log.error(e);
			return false;
		}
	}

	public String getUserIdFromJwt(String jwt) {
		log.info(Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(jwt).getBody().getSubject());
		return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(jwt).getBody().getSubject();
	}

}
