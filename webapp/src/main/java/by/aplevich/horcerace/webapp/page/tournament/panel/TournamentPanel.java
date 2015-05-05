package by.aplevich.horcerace.webapp.page.tournament.panel;

import by.aplevich.horcerace.datamodel.Place;
import by.aplevich.horcerace.datamodel.Race;
import by.aplevich.horcerace.services.RaceService;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import javax.inject.Inject;

public class TournamentPanel extends Panel{

    @Inject
    private RaceService raceService;
    private Place place;
    private Race race;

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

        liContainer.add(new Label("name", new Model<String>(place.getName())));
    }
}
