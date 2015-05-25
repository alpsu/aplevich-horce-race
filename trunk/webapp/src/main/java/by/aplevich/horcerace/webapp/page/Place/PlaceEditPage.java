package by.aplevich.horcerace.webapp.page.Place;

import by.aplevich.horcerace.datamodel.Place;
import by.aplevich.horcerace.services.PlaceService;
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
public class PlaceEditPage extends BaseLayout {
    @Inject
    private PlaceService placeService;

    private PageCreator pageCreator;

    public PlaceEditPage(final Place place, PageCreator pageCreator) {
        super();
        this.pageCreator = pageCreator;

        Form<Place> form = new Form<>("form", new CompoundPropertyModel<Place>(place));

        final TextField<String> tfName = new TextField<>("name");
        tfName.add(new PropertyValidator<String>());
        tfName.setLabel(new ResourceModel("p.placeEdit.name"));
        form.add(tfName);

        form.add(new SubmitLink("sumbit-link") {
            @Override
            public void onSubmit() {
                super.onSubmit();
                placeService.saveOrUpdate(place);
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