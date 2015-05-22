package by.aplevich.horcerace.webapp.page.login;

import by.aplevich.horcerace.webapp.app.BasicAuthenticationSession;
import by.aplevich.horcerace.webapp.page.BaseLayout;
import org.apache.wicket.Application;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.model.*;
import org.apache.wicket.util.string.Strings;

public class LoginPage extends BaseLayout {

	public static final String ID_FORM = "form";
	public static final String ID_PASSWORD = "password";
	public static final String ID_USERNAME = "username";

	private String username;
	private String password;

	@Override
	protected void onInitialize() {
		super.onInitialize();

		// if already logged then should not see login page at all
		if (AuthenticatedWebSession.get().isSignedIn()) {
			setResponsePage(Application.get().getHomePage());
		}
		final Form<Void> form = new Form<Void>(ID_FORM) {

			@Override
			protected void onSubmit() {
				if (Strings.isEmpty(username) || Strings.isEmpty(password)) {
					return;
				}
				final boolean authResult = AuthenticatedWebSession.get().signIn(username, password);
				if (authResult) {
					// continueToOriginalDestination();
					setResponsePage(Application.get().getHomePage());
				} else {
					//error("Ошибка авторизации");
					BasicAuthenticationSession.get().error(new StringResourceModel("error.user.auth", this, null).getString());
				}
			}
		};

		form.setDefaultModel(new CompoundPropertyModel<LoginPage>(this));
		form.add(new RequiredTextField<String>(ID_USERNAME));
		form.add(new PasswordTextField(ID_PASSWORD));
		add(form);
	}

	@Override
	protected IModel<String> getPageTitle() {
		return new ResourceModel("p.login.text");
	}

}
