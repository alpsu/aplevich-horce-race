package by.aplevich.horcerace.webapp.page.login.component;

import by.aplevich.horcerace.datamodel.UserAccount;
import by.aplevich.horcerace.webapp.app.BasicAuthenticationSession;
import by.aplevich.horcerace.webapp.app.WicketWebApplication;
import by.aplevich.horcerace.webapp.page.login.LoginPage;
import by.aplevich.horcerace.webapp.page.user.UserEditPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.http.handler.RedirectRequestHandler;

import javax.servlet.http.HttpServletRequest;

public class LoginLogoutPanel extends Panel {

	public LoginLogoutPanel(String id) {
		super(id);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		add(new Link("login-btn") {
			@Override
			protected void onConfigure() {
				super.onConfigure();
				boolean isLoginPage = LoginPage.class.equals(getPage().getClass());
				boolean isLogged = BasicAuthenticationSession.get().isSignedIn();
				setVisible(!(isLogged || isLoginPage));
			}

			@Override
			public void onClick() {
				setResponsePage(new LoginPage());
			}
		});

		add(new Link("logout-btn") {
			@Override
			protected void onConfigure() {
				super.onConfigure();
				setVisible(BasicAuthenticationSession.get().isSignedIn());
			}

			@Override
			public void onClick() {
				final HttpServletRequest servletReq = (HttpServletRequest) getRequest().getContainerRequest();
				servletReq.getSession().invalidate();
				getSession().invalidate();
				getRequestCycle().scheduleRequestHandlerAfterCurrent(new RedirectRequestHandler(WicketWebApplication.HOME_URL));

			}
		});

		UserAccount user = BasicAuthenticationSession.get().getUser();
		add(new Label("userName", new Model(user != null ? user.getName() : null)));
        add(new Link("create-user-btn") {
            @Override
            public void onClick() {
                setResponsePage((new UserEditPage(new UserAccount())));
            }
        });
        add(new Link("edit-user-btn") {
            @Override
            protected void onConfigure() {
                super.onConfigure();
                setVisible(BasicAuthenticationSession.get().isSignedIn());
            }

            @Override
            public void onClick() {
                setResponsePage((new UserEditPage(user)));
            }
        });
    }
}