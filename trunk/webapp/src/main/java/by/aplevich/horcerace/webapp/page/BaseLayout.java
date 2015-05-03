package by.aplevich.horcerace.webapp.page;

import by.aplevich.horcerace.datamodel.Place;
import by.aplevich.horcerace.services.PlaceService;
import by.aplevich.horcerace.webapp.page.panel.PlacePanel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import javax.inject.Inject;
import java.util.List;

public abstract class BaseLayout extends WebPage {

    @Inject
    private PlaceService placeService;

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new Label("headerTitle", getPageTitle()));
        final List<Place> allPlaces = placeService.getAllPlaces();
        add(new ListView<Place>("list-panel", allPlaces) {
            @Override
            protected void populateItem(ListItem<Place> item) {
                Place place = item.getModelObject();
                item.add(new Label("id", place.getId()).setVisible(false));
                item.add((new PlacePanel("place-panel", place)));
            }
        });
    }

    protected IModel<String> getPageTitle() {
        return new Model<String>(getClass().getSimpleName());
    }
}