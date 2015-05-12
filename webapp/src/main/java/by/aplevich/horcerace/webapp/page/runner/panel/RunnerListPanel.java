package by.aplevich.horcerace.webapp.page.runner.panel;

import by.aplevich.horcerace.datamodel.Bet;
import by.aplevich.horcerace.datamodel.Race;
import by.aplevich.horcerace.datamodel.Runner;
import by.aplevich.horcerace.datamodel.Runner_;
import by.aplevich.horcerace.services.RaceService;
import by.aplevich.horcerace.services.RunnerService;
import by.aplevich.horcerace.webapp.page.bet.BetEditPage;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import javax.inject.Inject;
import javax.persistence.metamodel.SingularAttribute;
import java.util.Iterator;
import java.util.List;

public class RunnerListPanel extends Panel {
    @Inject
    private RaceService raceService;

    @Inject
    private RunnerService runnerService;

    private List<Runner> runners;

    private Long raceId;
    private Long placeId;

    public RunnerListPanel(String id, Long placeId, Long raceId) {
        super(id);
        this.placeId = placeId;
        this.raceId = raceId;

        RunnerDataProvider runnerDataProvider = new RunnerDataProvider();

        WebMarkupContainer tableBody = new WebMarkupContainer("wrapper-body");
        add(tableBody);
        DataView<Runner> dataView = new DataView<Runner>("list", runnerDataProvider, runnerDataProvider.size()) {
            @Override
            protected void populateItem(Item<Runner> item) {
                Runner runner = item.getModelObject();

                item.add(new Label("number", runner.getPlace()));
                item.add(new Label("horce", runner.getHorce().getName()));
                item.add(new Label("trainer", runner.getHorce().getTrainer()));
                item.add(new Label("jockey", runner.getJockey().getName()));
                item.add(new Label("age", runner.getHorce().getAge()));
                //item.add(new Label("koef", runner.getKoefficient()));

                Link actionLink = new Link<String>("linkBet") {
                    @Override
                    public void onClick() {
                        setResponsePage(new BetEditPage(new Bet()));
                    }
                };
                item.add(actionLink.add(new Label("koef", runner.getKoefficient())));
            }
        };
        tableBody.add(dataView);
        add(new OrderByBorder<SingularAttribute<Runner, ?>>("sortByNum", Runner_.place, runnerDataProvider));
        add(new OrderByBorder<SingularAttribute<Runner, ?>>("sortByKoef", Runner_.koefficient, runnerDataProvider));
    }

    private class RunnerDataProvider extends SortableDataProvider<Runner, SingularAttribute<Runner, ?>>{

        public RunnerDataProvider() {
            super();
            setSort(Runner_.place, SortOrder.DESCENDING);
        }

        @Override
        public Iterator<? extends Runner> iterator(long first, long count) {
            SingularAttribute<Runner, ?> sortParam = getSort().getProperty();
            SortOrder propertySortOrder = getSortState().getPropertySortOrder(sortParam);
            boolean ascending = SortOrder.ASCENDING.equals(propertySortOrder);
            return runnerService.getAllRunnerByRace(raceId, sortParam, ascending).iterator();
        }

        @Override
        public long size() {
            return runnerService.getCount(raceId);
        }

        @Override
        public IModel<Runner> model(Runner runner) {
            return new CompoundPropertyModel<Runner>(runner);
        }
    }
}