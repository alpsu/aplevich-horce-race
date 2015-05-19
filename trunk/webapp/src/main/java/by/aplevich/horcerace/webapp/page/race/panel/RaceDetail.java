package by.aplevich.horcerace.webapp.page.race.panel;

import by.aplevich.horcerace.datamodel.Bet;
import by.aplevich.horcerace.datamodel.Runner;
import by.aplevich.horcerace.datamodel.Runner_;
import by.aplevich.horcerace.services.RaceService;
import by.aplevich.horcerace.services.RunnerService;
import by.aplevich.horcerace.webapp.page.bet.BetEditPage;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import javax.inject.Inject;
import javax.persistence.metamodel.SingularAttribute;
import java.util.Iterator;

public class RaceDetail extends Panel {
    @Inject
    private RaceService raceService;

    @Inject
    private RunnerService runnerService;

    private Long raceId;

    public RaceDetail(String id, Long raceId) {
        super(id);
        this.raceId = raceId;
        setOutputMarkupId(true);
        RunnerDataProvider runnerDataProvider = new RunnerDataProvider();

        add(new Label("startTime", raceService.get(raceId).getStart().toString()));
        add(new Label("desc", raceService.get(raceId).getDescription()));
        add(new Label("dist", raceService.get(raceId).getDistance()));
        add(new Label("quant", raceService.get(raceId).getQuantity()));
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

                Link actionLink = new Link<String>("linkBet") {
                    @Override
                    public void onClick() {
                        setResponsePage(new BetEditPage(new Bet(), runner));
                    }
                };
                item.add(actionLink.add(new Label("koef", runner.getKoefficient())));
            }
        };
        tableBody.add(dataView);
    }

    private class RunnerDataProvider extends SortableDataProvider<Runner, SingularAttribute<Runner, ?>> {

        public RunnerDataProvider() {
            super();
            setSort(Runner_.place, SortOrder.ASCENDING);
        }

        @Override
        public Iterator<? extends Runner> iterator(long first, long count) {
            SingularAttribute<Runner, ?> sortParam = getSort().getProperty();
            SortOrder propertySortOrder = getSortState().getPropertySortOrder(sortParam);
            boolean ascending = SortOrder.ASCENDING.equals(propertySortOrder);

            return runnerService.getAllRunnerByRace(raceId, sortParam, ascending, (int) first, (int) count).iterator();
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