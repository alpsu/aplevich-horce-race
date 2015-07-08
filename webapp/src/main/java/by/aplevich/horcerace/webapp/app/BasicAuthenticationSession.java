package by.aplevich.horcerace.webapp.app;

import by.aplevich.horcerace.datamodel.UserAccount;
import by.aplevich.horcerace.datamodel.enums.UserRole;
import by.aplevich.horcerace.services.UserService;
import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.request.Request;

import javax.inject.Inject;
import java.util.List;

public class BasicAuthenticationSession extends AuthenticatedWebSession {
    private UserAccount user;
    private Roles resultRoles;

    @Inject
    private UserService userService;

    public BasicAuthenticationSession(final Request request) {
        super(request);
        Injector.get().inject(this);
    }

    public static BasicAuthenticationSession get() {
        return (BasicAuthenticationSession) Session.get();
    }

    @Override
    public boolean authenticate(final String userLogin, final String password) {
        boolean authenticationResult = false;
        final UserAccount account = userService.getUserByLogin(userLogin);
        if (account != null && account.getPassword().equals(password)) {
            this.user = account;
            authenticationResult = true;
        }
        return authenticationResult;
    }

    @Override
    public Roles getRoles() {
        if (isSignedIn() && (resultRoles == null)) {
            resultRoles = new Roles();
            List<UserRole> roles = userService.getRoles(user.getId());
            for (UserRole role : roles) {
                resultRoles.add(role.name());
            }
        }
        return resultRoles;
    }

    @Override
    public void signOut() {
        super.signOut();
        user = null;
    }

    public UserAccount getUser() {
        return user;
    }
}