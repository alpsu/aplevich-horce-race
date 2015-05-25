package by.aplevich.horcerace.webapp.page.panel;

import by.aplevich.horcerace.datamodel.Place;
import by.aplevich.horcerace.webapp.page.tournament.TournamentDetailsPage;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

public class PlacePanel extends Panel {
    private Place place;

    public PlacePanel(String id, Place place) {
        super(id);
        this.place = place;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        WebMarkupContainer liContainer = new WebMarkupContainer("wrapper-li");
        add(liContainer);

        Link actionLink = new Link<String>("link") {
            @Override
            public void onClick() {
                setResponsePage(new TournamentDetailsPage(place));
            }
        };
        liContainer.add(actionLink.add(new Label("linkname", new Model<>(place.getName()))));
    }
}