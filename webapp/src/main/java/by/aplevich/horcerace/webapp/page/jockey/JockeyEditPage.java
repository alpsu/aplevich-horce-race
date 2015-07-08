package by.aplevich.horcerace.webapp.page.jockey;

import by.aplevich.horcerace.datamodel.Jockey;
import by.aplevich.horcerace.services.JockeyService;
import by.aplevich.horcerace.webapp.page.BaseLayout;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.bean.validation.PropertyValidator;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow.PageCreator;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;

import javax.inject.Inject;

//called by edit page only for admin
@AuthorizeInstantiation({"ADMIN"})
public class JockeyEditPage extends BaseLayout {

    @Inject
    private JockeyService jockeyService;

    private PageCreator pageCreator;

    public JockeyEditPage(final Jockey jockey, PageCreator pageCreator) {
        super();
        this.pageCreator = pageCreator;

        Form<Jockey> form = new Form<>("form", new CompoundPropertyModel<Jockey>(jockey));

        final TextField<String> tffName = new TextField<>("fname");
        tffName.add(new PropertyValidator<String>());
        tffName.setLabel(new ResourceModel("p.jockeyEdit.fname"));
        form.add(tffName);

        final TextField<String> tflName = new TextField<>("lname");
        tflName.add(new PropertyValidator<String>());
        tflName.setLabel(new ResourceModel("p.jockeyEdit.lname"));
        form.add(tflName);

        form.add(new SubmitLink("sumbit-link") {
            @Override
            public void onSubmit() {
                super.onSubmit();
                jockeyService.saveOrUpdate(jockey);
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