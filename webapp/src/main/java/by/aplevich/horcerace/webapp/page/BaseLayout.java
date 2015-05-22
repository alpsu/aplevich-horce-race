package by.aplevich.horcerace.webapp.page;

import by.aplevich.horcerace.datamodel.Place;
import by.aplevich.horcerace.datamodel.Place_;
import by.aplevich.horcerace.services.PlaceService;
import by.aplevich.horcerace.services.RaceService;
import by.aplevich.horcerace.webapp.app.BasicAuthenticationSession;
import by.aplevich.horcerace.webapp.page.bet.panel.BetPanel;
import by.aplevich.horcerace.webapp.page.login.component.LoginLogoutPanel;
import by.aplevich.horcerace.webapp.page.panel.PlacePanel;
import by.aplevich.horcerace.webapp.page.tournament.HelpPage;
import by.aplevich.horcerace.webapp.page.tournament.panel.LanguagePanel;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import javax.inject.Inject;
import java.util.List;

public abstract class BaseLayout extends WebPage {

    @Inject
    private PlaceService placeService;
    @Inject
    private RaceService raceService;

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new Label("headerTitle", getPageTitle()));
        add(new FeedbackPanel("feedbackpanel"));
        add(new LoginLogoutPanel("login-logout-panel"));
        add(new LanguagePanel("language-panel"));
        add(new BetPanel("bet-panel"));
        add(new Link("edit-btn") {
            @Override
            protected void onConfigure() {
                super.onConfigure();
                Roles roles = BasicAuthenticationSession.get().getRoles();
                if (roles != null && roles.hasRole("ADMIN")) {
                    setVisible(true);
                } else {
                    setVisible(false);
                }
            }

            @Override
            public void onClick() {
                //setResponsePage(new EditPage());
            }
        });

        add(new Link("help") {
            @Override
            public void onClick() {
                setResponsePage(new HelpPage());
            }
        });

        final List<Place> allPlaces = placeService.getAllPlaces(Place_.name,true,0,0);
        add(new ListView<Place>("list-panel", allPlaces) {
            @Override
            protected void populateItem(ListItem<Place> item) {
                Place place = item.getModelObject();
                /*if (raceService.getAllRaceByPlace(place).size() > 0) {
                    item.add((new PlacePanel("place-panel", place)));
                }*/
                item.add((new PlacePanel("place-panel", place)));
            }
        });
    }

    protected IModel<String> getPageTitle() {
        return new Model<String>(getClass().getSimpleName());
    }
}