package by.aplevich.horcerace.webapp.page.horse;

import by.aplevich.horcerace.datamodel.Horce;
import by.aplevich.horcerace.services.HorceService;
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

@AuthorizeInstantiation({"ADMIN"})
public class HorceEditPage extends BaseLayout {

    @Inject
    private HorceService horceService;

    private PageCreator pageCreator;

    public HorceEditPage(final Horce horce, PageCreator pageCreator) {
        super();
        this.pageCreator = pageCreator;

        Form<Horce> form = new Form<>("form", new CompoundPropertyModel<Horce>(horce));

        final TextField<String> tfName = new TextField<>("name");
        tfName.add(new PropertyValidator<String>());
        tfName.setLabel(new ResourceModel("p.horceEdit.name"));
        form.add(tfName);

        final TextField<String> tfTrainer = new TextField<>("trainer");
        tfTrainer.add(new PropertyValidator<String>());
        tfTrainer.setLabel(new ResourceModel("p.horceEdit.trainer"));
        form.add(tfTrainer);

        final TextField<Integer> tfAge = new TextField<>("age");
        tfAge.add(new PropertyValidator<Integer>());
        tfAge.setLabel(new ResourceModel("p.horceEdit.age"));
        form.add(tfAge);

        form.add(new SubmitLink("sumbit-link") {
            @Override
            public void onSubmit() {
                super.onSubmit();
                horceService.saveOrUpdate(horce);
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