package by.aplevich.horcerace.webapp.page.Place;

import by.aplevich.horcerace.datamodel.Place;
import by.aplevich.horcerace.services.PlaceService;
import by.aplevich.horcerace.webapp.page.BaseLayout;
import by.aplevich.horcerace.webapp.page.home.HomePage;
import org.apache.wicket.bean.validation.PropertyValidator;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;

import javax.inject.Inject;


public class PlaceEditPage extends BaseLayout {

    @Inject
    private PlaceService placeService;

    public PlaceEditPage(final Place place) {
        super();
        Form<Place> form = new Form<>("form", new CompoundPropertyModel<Place>(place));

        final TextField<String> tfName = new TextField<>("name");
        tfName.add(new PropertyValidator<String>());
        form.add(tfName);

        form.add(new SubmitLink("sumbit-link") {
            @Override
            public void onSubmit() {
                super.onSubmit();
                placeService.saveOrUpdate(place);

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