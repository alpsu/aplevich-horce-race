package by.aplevich.horcerace.webapp.page.race;

import by.aplevich.horcerace.datamodel.Place;
import by.aplevich.horcerace.datamodel.Race;
import by.aplevich.horcerace.services.PlaceService;
import by.aplevich.horcerace.services.RaceService;
import by.aplevich.horcerace.webapp.page.BaseLayout;
import by.aplevich.horcerace.webapp.page.home.HomePage;
import by.aplevich.horcerace.webapp.utils.renderer.PlaceChoiceRenderer;
import org.apache.wicket.bean.validation.PropertyValidator;
import org.apache.wicket.extensions.yui.calendar.DateField;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;

import javax.inject.Inject;
import java.util.Date;

public class RaceEditPage extends BaseLayout {

    @Inject
    private RaceService raceService;

    @Inject
    private PlaceService placeService;

    public RaceEditPage(final Race race) {
        super();
        Form<Race> form = new Form<>("form");

        form.add(new DropDownChoice<Place>("place", placeService.getAllPlaces(), PlaceChoiceRenderer.INSTANCE));

        final TextField<String> tfDesc = new TextField<>("description", new  PropertyModel(race, "description"));
        tfDesc.add(new PropertyValidator<String>());
        tfDesc.setLabel(new ResourceModel("p.raceEdit.desc"));
        form.add(tfDesc);

        final TextField<String> tfDist = new TextField<>("distance", new  PropertyModel(race, "distance"));
        tfDist.add(new PropertyValidator<String>());
        tfDist.setLabel(new ResourceModel("p.raceEdit.dist"));
        form.add(tfDist);

        final DateField fDate = new DateField("start", new  PropertyModel(race, "start"));
        fDate.add(new PropertyValidator<Date>());
        fDate.setLabel(new ResourceModel("p.raceEdit.date"));
        form.add(fDate);

        final TextField<Integer> tfquant = new TextField<>("quantity", new PropertyModel(race, "quantity"));
        tfquant.add(new PropertyValidator<Integer>());
        tfquant.setLabel(new ResourceModel("p.raceEdit.quantity"));
        form.add(tfquant);

        form.add(new SubmitLink("sumbit-link") {
            @Override
            public void onSubmit() {
                super.onSubmit();
                raceService.saveOrUpdate(race);

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