package by.aplevich.horcerace.webapp.page.tournament.panel;

import by.aplevich.horcerace.datamodel.Place;
import by.aplevich.horcerace.webapp.page.Place.PlaceEditPage;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class TournamentPanel extends Panel{

    //@Inject
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

        liContainer.add(new Label("name", new Model<String>(place.getName())));
        liContainer.add(new Link("create-new-place-link") {
            @Override
            public void onClick() {
                setResponsePage(new PlaceEditPage(new Place()));
            }
        });
        liContainer.add(new Link("edit-place-link") {
            @Override
            public void onClick() {
                setResponsePage(new PlaceEditPage(place));
            }
        });
    }
}
