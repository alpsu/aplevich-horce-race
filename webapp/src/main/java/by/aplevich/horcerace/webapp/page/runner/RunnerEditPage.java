package by.aplevich.horcerace.webapp.page.runner;

import by.aplevich.horcerace.datamodel.Horce;
import by.aplevich.horcerace.datamodel.Jockey;
import by.aplevich.horcerace.datamodel.Runner;
import by.aplevich.horcerace.services.HorceService;
import by.aplevich.horcerace.services.JockeyService;
import by.aplevich.horcerace.services.RunnerService;
import by.aplevich.horcerace.webapp.page.BaseLayout;
import by.aplevich.horcerace.webapp.page.home.HomePage;
import by.aplevich.horcerace.webapp.utils.renderer.HorceChoiceRenderer;
import by.aplevich.horcerace.webapp.utils.renderer.JockeyChoiceRenderer;
import org.apache.wicket.bean.validation.PropertyValidator;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.ResourceModel;

import javax.inject.Inject;
import java.math.BigDecimal;

public class RunnerEditPage extends BaseLayout {

    @Inject
    private RunnerService runnerService;

    @Inject
    private HorceService horceService;

    @Inject
    private JockeyService jockeyService;

    public RunnerEditPage(final Runner runner) {
        super();
        Form<Runner> form = new Form<>("runnerForm");

        form.add(new DropDownChoice<Horce>("horce", horceService.getAllHorces(), HorceChoiceRenderer.INSTANCE));
        form.add(new DropDownChoice<Jockey>("jockey", jockeyService.getAllJockeys(), JockeyChoiceRenderer.INSTANCE));

        final TextField<String> tfKoef = new TextField<>("koefficient");
        tfKoef.add(new PropertyValidator<BigDecimal>());
        tfKoef.setLabel(new ResourceModel("p.runnerEdit.koefficient"));
        form.add(tfKoef);

        final TextField<String> tfPlace = new TextField<>("place");
        tfPlace.add(new PropertyValidator<String>());
        tfPlace.setLabel(new ResourceModel("p.runnerEdit.place"));
        form.add(tfPlace);

        form.add(new SubmitLink("sumbit-link") {
            @Override
            public void onSubmit() {
                super.onSubmit();
                runnerService.saveOrUpdate(runner);

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