package by.aplevich.horcerace.webapp.page.race;

import by.aplevich.horcerace.datamodel.Race;
import by.aplevich.horcerace.services.RaceService;
import by.aplevich.horcerace.webapp.page.BaseLayout;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.bean.validation.PropertyValidator;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow.PageCreator;
import org.apache.wicket.extensions.yui.calendar.DateTimeField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;

import javax.inject.Inject;
import java.util.Date;

//called by edit page only for admin
@AuthorizeInstantiation({"ADMIN"})
public class RaceEditPage extends BaseLayout {

    @Inject
    private RaceService raceService;

    private PageCreator pageCreator;

    public RaceEditPage(final Race race, PageCreator pageCreator) {
        super();
        this.pageCreator = pageCreator;

        Form<Race> form = new Form<>("form");

        final TextField<String> tfDesc = new TextField<>("description", new PropertyModel<>(race, "description"));
        tfDesc.add(new PropertyValidator<String>());
        tfDesc.setLabel(new ResourceModel("p.raceEdit.desc"));
        form.add(tfDesc);

        final TextField<String> tfDist = new TextField<>("distance", new PropertyModel<>(race, "distance"));
        tfDist.add(new PropertyValidator<String>());
        tfDist.setLabel(new ResourceModel("p.raceEdit.dist"));
        form.add(tfDist);

        final DateTimeField fDateTime = new DateTimeField("start", new PropertyModel<>(race, "start"));
        fDateTime.add(new PropertyValidator<Date>());
        fDateTime.setLabel(new ResourceModel("p.raceEdit.date"));
        form.add(fDateTime);

        final TextField<Integer> tfQuant = new TextField<>("quantity", new PropertyModel<>(race, "quantity"));
        tfQuant.add(new PropertyValidator<Integer>());
        tfQuant.setLabel(new ResourceModel("p.raceEdit.quantity"));
        form.add(tfQuant);

        form.add(new SubmitLink("sumbit-link") {
            @Override
            public void onSubmit() {
                super.onSubmit();
                raceService.saveOrUpdate(race);
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