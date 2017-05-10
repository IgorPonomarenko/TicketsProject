package epam.mentoring.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Igor_Ponomarenko on 18.04.2017.
 */
@Component
public class AuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    Account account;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //{"userId":12,"rolesAssigned":["buyer","seller"]}
        String headerAccount = request.getHeader("account");

        if (headerAccount != null) {
            Account requestAccount = new ObjectMapper().readValue(headerAccount, Account.class);
            account.setUserId(requestAccount.getUserId());
            account.setRolesAssigned(requestAccount.getRolesAssigned());
        }
        filterChain.doFilter(request, response);
    }
}
