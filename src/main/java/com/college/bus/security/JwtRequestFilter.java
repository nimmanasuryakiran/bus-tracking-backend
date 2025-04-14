package com.college.bus.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;



import java.security.Key;
import java.util.Collections;
import java.util.List;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    private static final String SECRET_KEY = "your-256-bit-secret-your-256-bit-secret"; // Must match JwtUtil
    
    

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
    

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
    	System.out.println("Before FilterChain - Auth: " + SecurityContextHolder.getContext().getAuthentication());
        
        

        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorizationHeader.substring(7);
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        String username = claims.getSubject();
        //String role = (String) claims.get("role"); // Extract role from token
       

        String role = claims.get("role", String.class);
        if (role != null) {
            role = "ROLE_" + role; // Add ROLE_ prefix
        }
        System.out.println("Extracted Role from Token: " + claims.get("role"));
        System.out.println("Username: " + username);
        System.out.println("Role: " + role);


        





		/*
		 * if (username != null && role != null) {
		 * 
		 * List<SimpleGrantedAuthority> authorities = Collections.singletonList(new
		 * SimpleGrantedAuthority(role)); System.out.println("Authorities extracted: " +
		 * authorities);
		 * 
		 * 
		 * UsernamePasswordAuthenticationToken authentication = new
		 * UsernamePasswordAuthenticationToken(username, null, authorities);
		 * 
		 * SecurityContextHolder.getContext().setAuthentication(authentication); }
		 * 
		 * filterChain.doFilter(request, response);
		 */
        if (username != null && role != null) {
            List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(role));
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(username, null, authorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);
            request.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext()); // 🔥 Persist SecurityContext

            System.out.println("Authentication set in SecurityContext: " + SecurityContextHolder.getContext().getAuthentication());
        }

       

        // ✅ Continue with the filter chain after setting authentication
        filterChain.doFilter(request, response);
        
        System.out.println("After FilterChain - Auth: " + SecurityContextHolder.getContext().getAuthentication());


    }
    
    
}
