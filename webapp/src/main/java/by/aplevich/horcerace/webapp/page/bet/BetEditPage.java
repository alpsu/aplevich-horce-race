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
import org.apache.wicket.model.ResourceModel;

import javax.inject.Inject;
import java.util.Arrays;

public class BetEditPage extends BaseLayout{

    @Inject
    private BetService betService;

    public BetEditPage(final Bet bet){
        super();
        Form<Bet> form = new Form<>("form");

        form.add(new DropDownChoice<BetType>("type", Arrays.asList(BetType.values()), TypeChoiceRenderer.INSTANCE));
        //form.add(new DropDownChoice<Runner>("runner", runnerService.getAllRunnerByRace(race), RunnerChoiceRenderer.INSTANCE));
        //form.add(new DropDownChoice<UserAccount>("user", Arrays.asList(BetType.values()), UserAccountChoiceRenderer.INSTANCE));
        form.add(new DropDownChoice<Currency>("currency", Arrays.asList(Currency.values()), CurrencyChoiceRenderer.INSTANCE));
;
        final TextField<String> tfSum = new TextField<>("sum");
        tfSum.add(new PropertyValidator<String>());
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