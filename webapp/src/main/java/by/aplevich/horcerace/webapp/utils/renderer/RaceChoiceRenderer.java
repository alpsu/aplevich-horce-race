package by.aplevich.horcerace.webapp.utils.renderer;

import by.aplevich.horcerace.datamodel.Race;
import by.aplevich.horcerace.services.RaceService;
import org.apache.wicket.markup.html.form.IChoiceRenderer;

import javax.inject.Inject;

public class RaceChoiceRenderer implements IChoiceRenderer<Race> {

    @Inject
    private RaceService raceService;


    public static RaceChoiceRenderer INSTANCE = new RaceChoiceRenderer();

    private RaceChoiceRenderer() {
        super();
    }

    @Override
    public Object getDisplayValue(Race race) {
        return race.getPlace().getName() + " : " + race.getStart();
    }

    @Override
    public String getIdValue(Race race, int index) {
        return race.getId().toString();
    }
}