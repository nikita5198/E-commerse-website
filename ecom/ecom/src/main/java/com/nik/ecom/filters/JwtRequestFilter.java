package com.nik.ecom.filters;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nik.ecom.services.jwt.UserDetailsServiceImpl;
import com.nik.ecom.utils.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
	
	
	private final UserDetailsServiceImpl userDetailsService;

	private final JwtUtil jwtUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	        throws ServletException, IOException {
	    String authHeader = request.getHeader("Authorization");
	    String token = null;
	    String username = null;

	    if (authHeader != null && authHeader.startsWith("Bearer ")) {
	        token = authHeader.substring(7);
	        logger.debug("Token: " + token);
	        try {
	            username = jwtUtil.extractUserName(token);
	            logger.debug("Username extracted: " + username);
	        } catch (Exception e) {
	            logger.error("Failed to extract username from token: {}");
	        }
	    }

	    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

	        if (jwtUtil.validateToken(token, userDetails)) {
	            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
	                    userDetails, null, userDetails.getAuthorities());
	            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	            SecurityContextHolder.getContext().setAuthentication(authToken);
	            logger.debug("Authentication set for user: " + username);
	        } else {
	            logger.debug("Token validation failed for user: " + username);
	        }
	    }
	    filterChain.doFilter(request, response);
	}

	
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		String authHeader=request.getHeader("Authorization");
//		String token=null;
//		String username=null;
//		if(authHeader !=null && authHeader.startsWith("Bearer ")){
//			token=authHeader.substring(7);
//			username=jwtUtil.extractUserName(token);
//		}
//		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
//			UserDetails userDetails=userDetailsService.loadUserByUsername(username);
//			
//			if(jwtUtil.validateToken(token, userDetails)) {
//				UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(userDetails,null);
//				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//				SecurityContextHolder.getContext().setAuthentication(authToken);;
//			}
//		}
//		filterChain.doFilter(request, response);
//		
//	}

}
