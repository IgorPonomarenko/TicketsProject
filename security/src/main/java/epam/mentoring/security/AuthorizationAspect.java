package epam.mentoring.security;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Igor_Ponomarenko on 18.04.2017.
 */
@Component
@Aspect
public class AuthorizationAspect {

    @Autowired
    Account account;

    @Around("execution(* *.*(..)) && @annotation(javax.annotation.security.RolesAllowed)")
    public Object authorizationCheck(ProceedingJoinPoint joinPoint) throws Throwable {

        boolean authorized = false;

        if (null != account.getRolesAssigned()) {

            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            RolesAllowed rolesAllowed = method.getAnnotation(RolesAllowed.class);
            ArrayList<String> methodRoles = new ArrayList(Arrays.asList(rolesAllowed.value()));

            for (String role : account.getRolesAssigned()) {
                if (methodRoles.contains(role)) {
                    authorized = true;
                    break;
                }
            }
        }

        if (authorized) {
            return joinPoint.proceed();
        } else {
            //logger : "User is not authorized to access " + method.getName() + " method"
            throw new AuthorizationException("User does not have sufficient permissions.");
        }
    }


}
