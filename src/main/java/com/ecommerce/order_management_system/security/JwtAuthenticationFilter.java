package com.ecommerce.order_management_system.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    //Client Request -> JWT Filter -> Extract Token -> Validate Token -> Load User
//-> Set Authentication -> Controller

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Reading Authorization Header

        final String authHeader = request.getHeader("Authorization");
        if(authHeader == null || !authHeader.startsWith("Bearer"))
        {
            filterChain.doFilter(request,response);
            return;
        }
        String jwt = authHeader.substring(7);
        String username = jwtService.extractUsername(jwt);

        // Authenticate User
        if(username != null && SecurityContextHolder.getContext().getAuthentication() ==null)
        {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if(jwtService.isTokenValid(jwt,userDetails))
            {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,null,userDetails.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);

                // Continue Filter Chain
                filterChain.doFilter(request,response); // always do this
            }
            // process we followed above

// JwtAuthenticationFilter -> Read Header -> Extract JWT -> Extract Username -> Load User
// -> Validate Token -> Set Authentication -> Continue Request.
        }


    }
}
