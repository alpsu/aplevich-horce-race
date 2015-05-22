package by.aplevich.horcerace.webapp.page.user;

import by.aplevich.horcerace.datamodel.UserAccount;
import by.aplevich.horcerace.datamodel.enums.UserRole;
import by.aplevich.horcerace.services.UserService;
import by.aplevich.horcerace.webapp.app.BasicAuthenticationSession;
import by.aplevich.horcerace.webapp.page.BaseLayout;
import by.aplevich.horcerace.webapp.utils.renderer.RoleChoiceRenderer;
import org.apache.wicket.Application;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.bean.validation.PropertyValidator;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.model.*;

import javax.inject.Inject;
import java.util.Arrays;

public class UserEditPage extends BaseLayout {

    @Inject
    private UserService userService;

    public UserEditPage(UserAccount userAccount) {
        super();
        Form<UserAccount> form = new Form<>("form", new CompoundPropertyModel<UserAccount>(userAccount));

        final TextField<String> tfName = new TextField<>("name");
        tfName.add(new PropertyValidator<String>());
        tfName.setLabel(new ResourceModel("p.userEdit.name"));
        form.add(tfName);

        final TextField<String> tfLogin = new TextField<>("login");
        tfLogin.add(new PropertyValidator<String>());
        tfLogin.setLabel(new ResourceModel("p.userEdit.login"));
        form.add(tfLogin);

        final PasswordTextField tfPassword = new PasswordTextField("password");
        tfPassword.add(new PropertyValidator<String>());
        tfPassword.setLabel(new ResourceModel("p.userEdit.password"));
        form.add(tfPassword);

        final PasswordTextField tfConfPassword = new PasswordTextField("cpassword");
        tfConfPassword.add(new PropertyValidator<String>());
        tfConfPassword.setLabel(new ResourceModel("p.userEdit.confirmPassword"));
        form.add(tfConfPassword);

        final DropDownChoice<UserRole> dsc = new DropDownChoice<>("role", Arrays.asList(UserRole.values()), RoleChoiceRenderer.INSTANCE);
        dsc.add(new PropertyValidator<UserRole>());
        dsc.setLabel(new ResourceModel("p.userEdit.role"));
        form.add(dsc);

        form.add(new SubmitLink("sumbit-link") {
                     @Override
                     public void onSubmit() {
                         super.onSubmit();

                         String value = tfLogin.getValue();
                         if (userService.getUserByLogin(value) == null) {
                             userService.createNewUser(userAccount);

                             AuthenticatedWebSession.get().signIn(userAccount.getLogin(), userAccount.getPassword());
                             setResponsePage(Application.get().getHomePage());
                         } else {

                             BasicAuthenticationSession.get().error(new StringResourceModel("error.user.login", this, null).getString());
                             UserEditPage page = new UserEditPage(userAccount);
                             setResponsePage(page);
                         }
                     }

                     @Override
                     public void onError() {

                         super.onError();
                     }
                 }
        );

        add(form);
        form.add(new
                        EqualPasswordInputValidator(tfPassword, tfConfPassword)
        );
    }

    @Override
    protected IModel<String> getPageTitle() {
        return new ResourceModel("p.userEdit.caption");
    }
}