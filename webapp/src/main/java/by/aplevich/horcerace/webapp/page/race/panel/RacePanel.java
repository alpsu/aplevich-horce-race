package by.aplevich.horcerace.webapp.page.race.panel;

import by.aplevich.horcerace.datamodel.Place;
import by.aplevich.horcerace.datamodel.Race;
import by.aplevich.horcerace.services.PlaceService;
import by.aplevich.horcerace.services.RaceService;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

import javax.inject.Inject;
import java.util.List;

public class RacePanel extends Panel{

    @Inject
    private RaceService raceService;

    @Inject
    private PlaceService placeService;

    private Long placeId;

    public RacePanel(String id, Long placeId) {
        super(id);
        this.placeId = placeId;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new ListView<Race>("race-detail", raceService.getAllRacesWithPlaceByPlace(placeService.get(placeId))) {
            @Override
            protected void populateItem(ListItem<Race> item) {
                Race race = item.getModelObject();
                item.add(new RaceDetail("listRaceDetail", placeId, race.getId()));
            }
        });
    }
}