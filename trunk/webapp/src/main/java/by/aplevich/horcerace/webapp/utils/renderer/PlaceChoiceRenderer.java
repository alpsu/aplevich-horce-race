package by.aplevich.horcerace.webapp.utils.renderer;

import by.aplevich.horcerace.datamodel.Place;
import org.apache.wicket.markup.html.form.IChoiceRenderer;

public class PlaceChoiceRenderer implements IChoiceRenderer<Place>{

    public static PlaceChoiceRenderer INSTANCE = new PlaceChoiceRenderer();

    private PlaceChoiceRenderer() {
        super();
    }

    @Override
    public Object getDisplayValue(Place place) {
        return place.getName();
    }

    @Override
    public String getIdValue(Place place, int index) {
        return place.getId().toString();
    }
}
