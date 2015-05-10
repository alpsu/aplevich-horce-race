package by.aplevich.horcerace.webapp.utils.renderer;

import by.aplevich.horcerace.datamodel.Race;
import org.apache.wicket.markup.html.form.IChoiceRenderer;

public class RaceChoiceRenderer implements IChoiceRenderer<Race> {

    public static RaceChoiceRenderer INSTANCE = new RaceChoiceRenderer();

    private RaceChoiceRenderer() {
        super();
    }

    @Override
    public Object getDisplayValue(Race race) {
        return race.getId() + " : " + race.getStart();
    }

    @Override
    public String getIdValue(Race race, int index) {
        return race.getId().toString();
    }
}
