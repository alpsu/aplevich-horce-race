package by.aplevich.horcerace.webapp.page.bet.panel;

import by.aplevich.horcerace.datamodel.Bet;
import by.aplevich.horcerace.datamodel.Bet_;
import by.aplevich.horcerace.datamodel.UserAccount;
import by.aplevich.horcerace.services.BetService;
import by.aplevich.horcerace.services.UserService;
import by.aplevich.horcerace.webapp.app.BasicAuthenticationSession;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import javax.inject.Inject;
import javax.persistence.metamodel.SingularAttribute;
import java.util.Iterator;

public class BetPanel extends Panel {
    @Inject
    private BetService betService;

    @Inject
    private UserService userService;

    private UserAccount user;

    public BetPanel(String id) {
        super(id);
        setOutputMarkupId(true);

        user = BasicAuthenticationSession.get().getUser();
        if (user == null) {
            user = userService.get(1L);
        }

        BetDataProvider betDataProvider = new BetDataProvider();

        WebMarkupContainer tableBody = new WebMarkupContainer("wrapper-body");
        add(tableBody);
        DataView<Bet> dataView = new DataView<Bet>("list", betDataProvider, 3) {
            @Override
            protected void populateItem(Item<Bet> item) {
                final Bet bet = item.getModelObject();

                item.add(new Label("currency", bet.getCurrency().toString()));
                item.add(new Label("sum", bet.getSum().toString()));
                item.add(new Label("type", bet.getType().toString()));
                item.add(new Label("sum2", betService.getWin(bet.getId()).toString()));
            }
        };
        tableBody.add(dataView);
        add(new PagingNavigator("paging", dataView));
    }

    private class BetDataProvider extends SortableDataProvider<Bet, SingularAttribute<Bet, ?>>{
        public BetDataProvider() {
            super();
            setSort(Bet_.sum, SortOrder.ASCENDING);
        }

        @Override
        public Iterator<? extends Bet> iterator(long first, long count) {
            SingularAttribute<Bet, ?> sortParam = getSort().getProperty();
            SortOrder propertySortOrder = getSortState().getPropertySortOrder(sortParam);
            boolean ascending = SortOrder.ASCENDING.equals(propertySortOrder);

            return betService.getAllBetsByUser(user.getId(), sortParam, ascending, (int) first, (int) count).iterator();
        }

        @Override
        public long size() {
            return betService.getCount(user);
        }

        @Override
        public IModel<Bet> model(Bet bet) {
            return new CompoundPropertyModel<Bet>(bet);
        }
    }
}