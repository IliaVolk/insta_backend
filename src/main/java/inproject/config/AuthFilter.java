package inproject.config;

import inproject.entity.InstagramAuthUser;
import inproject.entity.UserType;
import inproject.repository.UserRepository;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@Component
public class AuthFilter extends OncePerRequestFilter {

    @Autowired
    UserRepository userRepository;
    private final static List<Long> ADMIN_ID = Arrays.asList(4315110671l);
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String method = request.getMethod();
        if (method.equals("OPTIONS")){
            filterChain.doFilter(request, response);
            return;
        }
        if (method.equals("GET")){
            InstagramAuthUser user = new InstagramAuthUser();
            user.setUserType(UserType.ANONYMOUS);
            filterChain.doFilter(request, response);
            return;
        }
        String authHeader = request.getHeader("Authorization");
        try{
            Long id = Long.parseLong(authHeader);
            InstagramAuthUser user = userRepository.findOne(id);
            if (user != null){
                if (ADMIN_ID.contains(id)){
                    user.setUserType(UserType.ADMIN);
                }else{
                    user.setUserType(UserType.USER);
                }
            }
            request.setAttribute("user", user);
            filterChain.doFilter(request, response);
            return;
        }catch (NumberFormatException e){
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
            response.getWriter().println("authHeader: "+authHeader + "\n" +e.getMessage());
        }

        response.setStatus(HttpStatus.SC_UNAUTHORIZED);
    }
}
