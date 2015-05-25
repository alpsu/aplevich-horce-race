package by.aplevich.horcerace.webapp.page.runner;

import by.aplevich.horcerace.datamodel.Horce;
import by.aplevich.horcerace.datamodel.Jockey;
import by.aplevich.horcerace.datamodel.Runner;
import by.aplevich.horcerace.services.HorceService;
import by.aplevich.horcerace.services.JockeyService;
import by.aplevich.horcerace.services.RunnerService;
import by.aplevich.horcerace.webapp.page.BaseLayout;
import by.aplevich.horcerace.webapp.utils.renderer.HorceChoiceRenderer;
import by.aplevich.horcerace.webapp.utils.renderer.JockeyChoiceRenderer;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.bean.validation.PropertyValidator;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow.PageCreator;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;

import javax.inject.Inject;
import java.math.BigDecimal;

@AuthorizeInstantiation({"ADMIN"})
public class RunnersEditPage extends BaseLayout {
    @Inject
    private RunnerService runnerService;
    @Inject
    private HorceService horceService;
    @Inject
    private JockeyService jockeyService;

    private PageCreator pageCreator;

    public RunnersEditPage(final Runner runner, PageCreator pageCreator) {
        super();
        this.pageCreator = pageCreator;

        Form<Runner> form = new Form<>("form");

        DropDownChoice<Horce> ddHorce = new DropDownChoice<Horce>("horce", new PropertyModel<>(runner, "horce"), horceService.getAllHorces(), HorceChoiceRenderer.INSTANCE);
        ddHorce.add(new PropertyValidator<Horce>());
        ddHorce.setLabel(new ResourceModel("p,runnerEdit.horce"));
        form.add(ddHorce);

        DropDownChoice<Jockey> ddJockey = new DropDownChoice<Jockey>("jockey", new PropertyModel<>(runner, "jockey"), jockeyService.getAllJockeys(), JockeyChoiceRenderer.INSTANCE);
        ddJockey.add(new PropertyValidator<Jockey>());
        ddJockey.setLabel(new ResourceModel("p.runnerEdit.jockey"));
        form.add(ddJockey);

        final TextField<BigDecimal> tfKoef = new TextField<>("koefficient", new PropertyModel<>(runner, "koefficient"));
        tfKoef.add(new PropertyValidator<BigDecimal>());
        tfKoef.setLabel(new ResourceModel("p.runnerEdit.koefficient"));
        form.add(tfKoef);

        final TextField<String> tfPlace = new TextField<>("place", new PropertyModel<>(runner, "place"));
        tfPlace.add(new PropertyValidator<String>());
        tfPlace.setLabel(new ResourceModel("p.runnerEdit.place"));
        form.add(tfPlace);

        form.add(new SubmitLink("sumbit-link") {
            @Override
            public void onSubmit() {
                super.onSubmit();
                runnerService.saveOrUpdate(runner);
                setResponsePage(pageCreator.createPage());
            }

            @Override
            public void onError() {
                super.onError();
            }
        });

        add(form);
    }
}