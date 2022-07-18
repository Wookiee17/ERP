package com.cloven.dev.erpdev.core.config;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cloven.dev.erpdev.core.dto.UserSession;
import com.cloven.dev.erpdev.core.filter.CognitoJwtAuthentication;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.source.RemoteJWKSet;
import com.nimbusds.jose.proc.BadJOSEException;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;

import lombok.extern.slf4j.Slf4j;

@ConfigurationProperties("cognito")
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

	private static final String AUTH_HEADER_STRING = "Authorization";
	private static final String AUTH_BEARER_STRING = "Bearer";
	private static final String EMPTY_STRING = "";

	private String ISSUER = "https://cognito-idp.ap-south-1.amazonaws.com/ap-south-1_IT7rYXJk0";
	private String KEY_STORE_PATH = "/.well-known/jwks.json";

	// should cache keys
	RemoteJWKSet<?> remoteJWKSet;

	public JwtAuthFilter() throws MalformedURLException {
		URL JWKUrl = new URL(ISSUER + KEY_STORE_PATH);
		this.remoteJWKSet = new RemoteJWKSet(JWKUrl);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		try {
			String header = req.getHeader(AUTH_HEADER_STRING).replace(AUTH_BEARER_STRING, "");

			logger.info(header);

			JWT jwt = JWTParser.parse(header);

			String iss = jwt.getJWTClaimsSet().getIssuer();
			logger.info(iss);

			// check if issuer is our user pool
			if (ISSUER.equals(jwt.getJWTClaimsSet().getIssuer())) {

				JWSKeySelector keySelector = new JWSVerificationKeySelector(JWSAlgorithm.RS256, remoteJWKSet);

				ConfigurableJWTProcessor<?> jwtProcessor = new DefaultJWTProcessor();
				jwtProcessor.setJWSKeySelector(keySelector);

				JWTClaimsSet claimsSet = jwtProcessor.process(jwt, null);

				List<GrantedAuthority> authorities = new ArrayList<>();

				String username = claimsSet.getClaims().get("username").toString();

				List<String> groups = (List<String>) claimsSet.getClaims().get("cognito:groups");
				List<GrantedAuthority> grantedAuthorities = convertList(groups,
						group -> new SimpleGrantedAuthority(group.toUpperCase()));

				UserSession user = new UserSession(username, username, grantedAuthorities);

				SecurityContextHolder.getContext()
						.setAuthentication(new CognitoJwtAuthentication(user, claimsSet, grantedAuthorities));

			}

		} catch (ParseException e) {
			e.printStackTrace();
		} catch (JOSEException e) {
			e.printStackTrace();
		} catch (BadJOSEException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			log.info("Header is Empty Hence Authentication Not Required");
		}

		chain.doFilter(req, res);
	}

	private static <T, U> List<U> convertList(List<T> from, Function<T, U> func) {
		return from.stream().map(func).collect(Collectors.toList());
	}

}