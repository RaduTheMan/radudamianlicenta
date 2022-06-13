package com.damian.gemixqueapi.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;

        setFilterProcessesUrl("/api/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        var gson = new Gson();
        UserDetailsProjection userCredentials = null;
        try {
            userCredentials = gson.fromJson(request.getReader(), UserDetailsObj.class );
        } catch (IOException e) {
            throw new BadCredentialsException("credentials couldn't be parsed");
        }
        String username = userCredentials.getUsername();
        String password = userCredentials.getPassword();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UserWithUuid user = (UserWithUuid)authResult.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256("hardcoded-secret".getBytes());
        String accessToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000)) //1 hour
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);
        response.setHeader("accessToken", accessToken);
        var gson = new Gson();
        Map<String, String> token = new HashMap<>();
        token.put("accessToken", accessToken);
        token.put("uuid", user.getUuid());
        response.setContentType(APPLICATION_JSON_VALUE);
        var writer = response.getWriter();
        writer.print(gson.toJson(token));
        writer.flush();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        var gson = new Gson();
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("errorMessage", "Username or password are incorrect.");
        var writer = response.getWriter();
        writer.print(gson.toJson(responseBody));
        writer.flush();
    }
}
