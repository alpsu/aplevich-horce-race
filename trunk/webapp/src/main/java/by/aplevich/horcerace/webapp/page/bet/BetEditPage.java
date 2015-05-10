package by.aplevich.horcerace.webapp.page.bet;

import by.aplevich.horcerace.datamodel.Bet;
import by.aplevich.horcerace.datamodel.enums.BetType;
import by.aplevich.horcerace.datamodel.enums.Currency;
import by.aplevich.horcerace.services.BetService;
import by.aplevich.horcerace.webapp.page.BaseLayout;
import by.aplevich.horcerace.webapp.page.home.HomePage;
import by.aplevich.horcerace.webapp.utils.renderer.CurrencyChoiceRenderer;
import by.aplevich.horcerace.webapp.utils.renderer.TypeChoiceRenderer;
import org.apache.wicket.bean.validation.PropertyValidator;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Arrays;

public class BetEditPage extends BaseLayout{

    @Inject
    private BetService betService;

    public BetEditPage(final Bet bet){
        super();
        Form<Bet> form = new Form<>("form");

        DropDownChoice<BetType> ddType = new DropDownChoice<>("type", new PropertyModel<>(bet, "type"), Arrays.asList(BetType.values()), TypeChoiceRenderer.INSTANCE);
        ddType.add(new PropertyValidator<BetType>());
        ddType.setLabel(new ResourceModel("p.betEdit.type"));
        form.add(ddType);

        //form.add(new DropDownChoice<Runner>("runner", runnerService.getAllRunnerByRace(race), RunnerChoiceRenderer.INSTANCE));
        //form.add(new DropDownChoice<UserAccount>("user", Arrays.asList(BetType.values()), UserAccountChoiceRenderer.INSTANCE));

        DropDownChoice<Currency> ddCurrency = new DropDownChoice<>("currency", new PropertyModel<>(bet, "currency"), Arrays.asList(Currency.values()), CurrencyChoiceRenderer.INSTANCE);
        ddCurrency.add(new PropertyValidator<Currency>());
        ddCurrency.setLabel(new ResourceModel("p.betEdit.currency"));
        form.add(ddCurrency);

        final TextField<BigDecimal> tfSum = new TextField<>("sum", new PropertyModel<>(bet, "sum"));
        tfSum.add(new PropertyValidator<BigDecimal>());
        tfSum.setLabel(new ResourceModel("p.betEdit.sum"));
        form.add(tfSum);
        
        form.add(new SubmitLink("sumbit-link") {
            @Override
            public void onSubmit() {
                super.onSubmit();
                betService.saveOrUpdate(bet);

                HomePage page = new HomePage();
                setResponsePage(page);
            }

            @Override
            public void onError() {

                super.onError();
            }
        });

        add(form);
    }
}