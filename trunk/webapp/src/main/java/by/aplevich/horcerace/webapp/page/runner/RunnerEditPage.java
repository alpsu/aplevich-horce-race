package by.aplevich.horcerace.webapp.page.runner;

import by.aplevich.horcerace.datamodel.Runner;
import by.aplevich.horcerace.services.RunnerService;
import by.aplevich.horcerace.webapp.app.BasicAuthenticationSession;
import by.aplevich.horcerace.webapp.page.BaseLayout;
import by.aplevich.horcerace.webapp.page.home.HomePage;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.bean.validation.PropertyValidator;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;

import javax.inject.Inject;
import java.math.BigDecimal;

public class RunnerEditPage extends BaseLayout {
    @Inject
    private RunnerService runnerService;

    public RunnerEditPage(final Long runnerId) {

        super();
        Roles roles = BasicAuthenticationSession.get().getRoles();

        boolean enabledAdm = roles.hasRole("ADMIN");
        boolean enabledBookie = roles.hasRole("BOOKIE");
        Runner runner = runnerService.getWithAllByRunner(runnerId);
        Form<Runner> form = new Form<>("form");

        final TextField<String> tfPlace = new TextField<>("place", new PropertyModel<>(runner, "place"));
        tfPlace.add(new PropertyValidator<String>());
        tfPlace.setLabel(new ResourceModel("p.runnerEdit.place"));
        tfPlace.setEnabled(enabledAdm);
        form.add(tfPlace);


        final TextField<String> tfHorce = new TextField<>("name", new PropertyModel<>(runner.getHorce(), "name"));
        tfHorce.add(new PropertyValidator<String>());
        tfHorce.setLabel(new ResourceModel("p.runnerEdit.horce"));
        tfHorce.setEnabled(false);
        form.add(tfHorce);

        final TextField<String> tfJockey = new TextField<>("jockey", new Model<>(runner.getJockey().getName()));
        tfJockey.setLabel(new ResourceModel("p.runnerEdit.jockey"));
        tfJockey.setEnabled(false);
        form.add(tfJockey);

        final TextField<BigDecimal> tfKoef = new TextField<>("koefficient", new PropertyModel<>(runner, "koefficient"));
        tfKoef.add(new PropertyValidator<BigDecimal>());
        tfKoef.setLabel(new ResourceModel("p.runnerEdit.koefficient"));
        tfKoef.setEnabled(enabledBookie);
        form.add(tfKoef);

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