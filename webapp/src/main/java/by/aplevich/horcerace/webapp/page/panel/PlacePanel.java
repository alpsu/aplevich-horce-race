package by.aplevich.horcerace.webapp.page.panel;

import by.aplevich.horcerace.datamodel.Place;
import by.aplevich.horcerace.services.PlaceService;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import javax.inject.Inject;

public class PlacePanel extends Panel {
    @Inject
    private PlaceService placeService;
    private Place place;

    public PlacePanel(String id, Place place) {
        super(id);
        this.place = place;
    }

    public PlacePanel(String id, IModel<?> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        WebMarkupContainer liContainer = new WebMarkupContainer("wrapper-li");
        add(liContainer);

        liContainer.add(new Label("name", new Model<String>(place.getName())));

    }
}
