package by.aplevich.horcerace.webapp.page.race.panel;

import by.aplevich.horcerace.datamodel.Race;
import by.aplevich.horcerace.datamodel.Runner;
import by.aplevich.horcerace.datamodel.Runner_;
import by.aplevich.horcerace.services.RaceService;
import by.aplevich.horcerace.services.RunnerService;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import javax.inject.Inject;
import javax.persistence.metamodel.SingularAttribute;
import java.util.Iterator;
import java.util.List;

public class RacePanel extends Panel{

    private List<Race> races;

    public RacePanel(String id, List<Race> races) {
        super(id);
        this.races = races;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        //add(new Label("race", races.get(0).getPlace().getName()));
        add(new ListView<Race>("race-detail", races) {
            @Override
            protected void populateItem(ListItem<Race> item) {
                Race race = item.getModelObject();
                item.add(new RaceDetail("listRaceDetail", race));
            }
        });
    }
}
