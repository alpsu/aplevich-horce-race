package by.aplevich.horcerace.webapp.page.user;

import by.aplevich.horcerace.datamodel.UserAccount;
import by.aplevich.horcerace.datamodel.enums.UserRole;
import by.aplevich.horcerace.services.UserService;
import by.aplevich.horcerace.webapp.page.BaseLayout;
import by.aplevich.horcerace.webapp.page.home.HomePage;
import by.aplevich.horcerace.webapp.utils.renderer.RoleChoiceRenderer;
import org.apache.wicket.bean.validation.PropertyValidator;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;

import javax.inject.Inject;
import java.util.Arrays;

public class UserEditPage extends BaseLayout{

    @Inject
    private UserService userService;

    public UserEditPage(final UserAccount userAccount) {
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

        final DropDownChoice<UserRole> dsc = new DropDownChoice<>("role", Arrays.asList(UserRole.values()), RoleChoiceRenderer.INSTANCE);
        dsc.add(new PropertyValidator<UserRole>());
        dsc.setLabel(new ResourceModel("p.userEdit.role"));
        form.add(dsc);

        form.add(new SubmitLink("sumbit-link") {
            @Override
            public void onSubmit() {
                super.onSubmit();
                userService.createNewUser(userAccount);

                HomePage page = new HomePage();
                setResponsePage(page);
            }

            @Override
            public void onError() {

                super.onError();
            }
        });

        add(form);
    }
}
