package by.aplevich.horcerace.webapp.page.tournament.panel;

import by.aplevich.horcerace.datamodel.*;
import by.aplevich.horcerace.services.RaceService;
import by.aplevich.horcerace.webapp.app.BasicAuthenticationSession;
import by.aplevich.horcerace.webapp.page.Place.PlaceEditPage;
import by.aplevich.horcerace.webapp.page.bet.BetEditPage;
import by.aplevich.horcerace.webapp.page.horse.HorceEditPage;
import by.aplevich.horcerace.webapp.page.jockey.JockeyEditPage;
import by.aplevich.horcerace.webapp.page.race.RaceEditPage;
import by.aplevich.horcerace.webapp.page.race.panel.RacePanel;
import by.aplevich.horcerace.webapp.page.runner.RunnerEditPage;
import by.aplevich.horcerace.webapp.page.user.UserEditPage;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import javax.inject.Inject;

public class TournamentPanel extends Panel{

    private Place place;

    public TournamentPanel(String id, Place place) {
        super(id);
        this.place = place;
    }

    public TournamentPanel(String id, Place place, IModel<?> model) {
        super(id, model);
        this.place = place;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        WebMarkupContainer liContainer = new WebMarkupContainer("wrapper-li");
        add(liContainer);

        liContainer.add(new RacePanel("race-panel", place.getId()));

        //add(new EditPanel("edit-panel", place));
    }
}