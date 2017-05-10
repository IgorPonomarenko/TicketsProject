package epam.mentoring.integrationservice.model;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

/**
 * Created by Igor_Ponomarenko on 18.04.2017.
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Account {
    public static final String ROLE_SELLER = "seller";
    public static final String ROLE_BUYER = "buyer";
    public static final String ROLE_INTERNAL_CALL = "internal_call";

    private int userId;
    private String[] rolesAssigned;

    protected Account() {
    }

    public Account(int userId, String[] rolesAssigned) {
        this.userId = userId;
        this.rolesAssigned = rolesAssigned;
    }

    public String[] getRolesAssigned() {
        return rolesAssigned;
    }

    public void setRolesAssigned(String[] rolesAssigned) {
        this.rolesAssigned = rolesAssigned;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(rolesAssigned, account.rolesAssigned);

    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(rolesAssigned);
    }

    @Override
    public String toString() {
        return "Account{" +
                "userId=" + userId +
                ", rolesAssigned=" + Arrays.toString(rolesAssigned) +
                '}';
    }
}
