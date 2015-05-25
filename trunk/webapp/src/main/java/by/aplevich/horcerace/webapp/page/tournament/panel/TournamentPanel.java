package by.aplevich.horcerace.webapp.page.tournament.panel;

import by.aplevich.horcerace.datamodel.Place;
import by.aplevich.horcerace.webapp.page.race.panel.RacePanel;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

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
    }
}