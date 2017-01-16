package inproject.config;

import org.apache.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AuthFilter extends OncePerRequestFilter {

    private final static List<Long> ADMIN_ID = Arrays.asList(4315110671l);
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String method = request.getMethod();
        if (method.equals("GET") || method.equals("OPTIONS")){
            filterChain.doFilter(request, response);
            return;
        }
        String authHeader = request.getHeader("Authorization");
        Long id = Long.parseLong(authHeader);
        if (ADMIN_ID.contains(id)){
            filterChain.doFilter(request, response);
            return;
        }
        response.setStatus(HttpStatus.SC_UNAUTHORIZED);
    }
}