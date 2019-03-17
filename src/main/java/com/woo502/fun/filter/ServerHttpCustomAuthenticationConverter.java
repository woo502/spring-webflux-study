package com.woo502.fun.filter;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.web.server.ServerWebExchange;

import com.nimbusds.jwt.SignedJWT;
import com.woo502.fun.common.jwt.JWTCustomVerifier;

import io.netty.util.internal.StringUtil;
import reactor.core.publisher.Mono;

public class ServerHttpCustomAuthenticationConverter implements ServerAuthenticationConverter {

	private  JWTCustomVerifier jwtVerifier = new JWTCustomVerifier();
	
	@Override
	public Mono<Authentication> convert(ServerWebExchange exchange) {
		String authKey = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
		if (StringUtil.isNullOrEmpty(authKey)) {
			authKey = exchange.getRequest().getQueryParams().getFirst("utoken");
		}
		
		return jwtVerifier.check(authKey)
				.flatMap(ServerHttpCustomAuthenticationConverter::create)
				.log();
	}

	public static Mono<Authentication> create(SignedJWT signedJWTMono) {
        SignedJWT signedJWT = signedJWTMono;
        String subject;
        String auths;
        List<GrantedAuthority> authorities;

        try {
            subject = signedJWT.getJWTClaimsSet().getSubject();
            auths = (String) signedJWT.getJWTClaimsSet().getClaim("roles");
        } catch (ParseException e) {
            return Mono.empty();
        }
        authorities = Stream.of(auths.split(","))
                .map(a -> new SimpleGrantedAuthority(a))
                .collect(Collectors.toList());
        return  Mono.justOrEmpty(new UsernamePasswordAuthenticationToken(subject, null, authorities));

    }
}
