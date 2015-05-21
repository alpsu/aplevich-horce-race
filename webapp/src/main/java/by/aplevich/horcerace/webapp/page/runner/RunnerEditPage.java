package by.aplevich.horcerace.webapp.page.runner;

import by.aplevich.horcerace.datamodel.Horce;
import by.aplevich.horcerace.datamodel.Jockey;
import by.aplevich.horcerace.datamodel.Race;
import by.aplevich.horcerace.datamodel.Runner;
import by.aplevich.horcerace.services.HorceService;
import by.aplevich.horcerace.services.JockeyService;
import by.aplevich.horcerace.services.RaceService;
import by.aplevich.horcerace.services.RunnerService;
import by.aplevich.horcerace.webapp.app.BasicAuthenticationSession;
import by.aplevich.horcerace.webapp.page.BaseLayout;
import by.aplevich.horcerace.webapp.page.home.HomePage;
import by.aplevich.horcerace.webapp.utils.renderer.HorceChoiceRenderer;
import by.aplevich.horcerace.webapp.utils.renderer.JockeyChoiceRenderer;
import by.aplevich.horcerace.webapp.utils.renderer.RaceChoiceRenderer;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.bean.validation.PropertyValidator;
import org.apache.wicket.markup.html.form.DropDownChoice;
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

    @Inject
    private HorceService horceService;

    @Inject
    private JockeyService jockeyService;

    @Inject
    private RaceService raceService;

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

//        DropDownChoice<Horce> ddHorce = new DropDownChoice<Horce>("horce", new PropertyModel<>(runner, "horce"), horceService.getAllHorces(), HorceChoiceRenderer.INSTANCE);
//        ddHorce.add(new PropertyValidator<Horce>());
//        ddHorce.setLabel(new ResourceModel("p.runnerEdit.horce"));
//        form.add(ddHorce);

        final TextField<String> tfJockey = new TextField<>("jockey", new Model<>(runner.getJockey().getName()));
        tfJockey.setLabel(new ResourceModel("p.runnerEdit.jockey"));
        tfJockey.setEnabled(false);
        form.add(tfJockey);

//        DropDownChoice<Jockey> ddJockey = new DropDownChoice<Jockey>("jockey", new PropertyModel<>(runner, "jockey"), jockeyService.getAllJockeys(), JockeyChoiceRenderer.INSTANCE);
//        ddJockey.add(new PropertyValidator<Jockey>());
//        ddJockey.setLabel(new ResourceModel("p.runnerEdit.jockey"));
//        form.add(ddJockey);


//        DropDownChoice<Race> ddRace = new DropDownChoice<Race>("race", new PropertyModel<>(runner, "race"), raceService.getAllRacesWithPlace(), RaceChoiceRenderer.INSTANCE);
//        ddRace.add(new PropertyValidator<Race>());
//        ddRace.setLabel(new ResourceModel("p.runnerEdit.race"));
//        form.add(ddRace);

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