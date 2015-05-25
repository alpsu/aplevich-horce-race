package by.aplevich.horcerace.webapp.page.race.panel;

import by.aplevich.horcerace.datamodel.Race;
import by.aplevich.horcerace.datamodel.Race_;
import by.aplevich.horcerace.services.RaceService;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import javax.inject.Inject;
import javax.persistence.metamodel.SingularAttribute;
import java.util.Iterator;

public class RacePanel extends Panel{

    @Inject
    private RaceService raceService;

    private Long placeId;

    public RacePanel(String id, Long placeId) {
        super(id);
        this.placeId = placeId;
        setOutputMarkupId(true);
        RaceDataProvider raceDataProvider = new RaceDataProvider();

        WebMarkupContainer tableBody = new WebMarkupContainer("wrapper-body");
        add(tableBody);
        DataView<Race> dataView = new DataView<Race>("list", raceDataProvider, 1) {
            @Override
            protected void populateItem(Item<Race> item) {
                Race race = item.getModelObject();
                item.add(new RaceDetail("detail", race.getId()));
            }
        };
        tableBody.add(dataView);
        add(new PagingNavigator("paging", dataView));
    }

    private class RaceDataProvider extends SortableDataProvider<Race, SingularAttribute<Race, ?>> {
        public RaceDataProvider() {
            super();
            setSort(Race_.start, SortOrder.ASCENDING);
        }

        @Override
        public Iterator<? extends Race> iterator(long first, long count) {
            SingularAttribute<Race, ?> sortParam = getSort().getProperty();
            SortOrder propertySortOrder = getSortState().getPropertySortOrder(sortParam);
            boolean ascending = SortOrder.ASCENDING.equals(propertySortOrder);
            return raceService.getAllRacesWithPlaceByPlace(placeId, sortParam, ascending, (int) first, (int) count).iterator();
        }

        @Override
        public long size() {
            return raceService.getCount(placeId);
        }

        @Override
        public IModel<Race> model(Race race) {
            return new CompoundPropertyModel<Race>(race);
        }
    }
}