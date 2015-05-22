package by.aplevich.horcerace.webapp.page.tournament.panel;

import by.aplevich.horcerace.datamodel.*;
import by.aplevich.horcerace.services.PlaceService;
import by.aplevich.horcerace.services.RaceService;
import by.aplevich.horcerace.webapp.page.BaseLayout;
import by.aplevich.horcerace.webapp.page.Place.PlaceEditPage;
import by.aplevich.horcerace.webapp.page.race.RaceEditPage;
import by.aplevich.horcerace.webapp.utils.renderer.PlaceChoiceRenderer;
import by.aplevich.horcerace.webapp.utils.renderer.RaceChoiceRenderer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

//@AuthorizeInstantiation({"ADMIN"})
public class EditPage extends BaseLayout {
    @Inject
    private PlaceService placeService;
    @Inject
    private RaceService raceService;

    private Place place;
    private Race race;
    private IModel<Place> ddModelPlace = new PropertyModel<Place>(this, "place");
    private IModel<Race> ddModelRace = new PropertyModel<Race>(this, "race");

    IModel<List<? extends Race>> races = new AbstractReadOnlyModel<List<? extends Race>>()
    {
        @Override
        public List<Race> getObject()
        {
            if (place == null)
            {
                return Collections.emptyList();
            }
            return raceService.getAllRacesWithPlaceByPlace(place);
        }
    };

    private Horce horce;
    private Jockey jockey;
    private Runner runner;

    public EditPage() {
        super();
    }

    public EditPage(String id, IModel<?> model) {
        super();
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        Form form = new Form("f1"){
//            @Override
//            protected void onSubmit() {
//                place = ddModelPlace.getObject();
//                if(place == null) {
//                    place = new Place();
//                }
//                setResponsePage(new PlaceEditPage(place));
//            }
        };

        DropDownChoice<Place> ddPlace = new DropDownChoice<Place>("place", ddModelPlace,  placeService.getAllPlaces(Place_.name, true, 0, 0), PlaceChoiceRenderer.INSTANCE);
        form.add(ddPlace);

        Link<String> editPlaceLink = new Link<String>("editPlace") {
            @Override
            public void onClick() {
                setResponsePage(new PlaceEditPage(place));
            }
        };
        form.add(editPlaceLink);

       Link<String> newPlaceLink = new Link<String>("newPlace") {
            @Override
            public void onClick() {
                setResponsePage(new PlaceEditPage(new Place()));
            }
        };
        form.add(newPlaceLink);

        DropDownChoice<Race> ddRace = new DropDownChoice<Race>("race", ddModelRace, races , RaceChoiceRenderer.INSTANCE);
        ddRace.setOutputMarkupId(true);
        form.add(ddRace);

        add(form);


        ddPlace.add(new AjaxFormComponentUpdatingBehavior("change")
        {
            @Override
            protected void onUpdate(AjaxRequestTarget target)
            {
                target.add(ddRace);
            }
        });

        Link<String> editRaceLink = new Link<String>("editRace") {
            @Override
            public void onClick() {
                setResponsePage(new RaceEditPage(race));
            }
        };
        form.add(editRaceLink);

        Link<String> newRaceLink = new Link<String>("newRace") {
            @Override
            public void onClick() {
                setResponsePage(new RaceEditPage(new Race()));
            }
        };

        form.add(newRaceLink);
    }

    @Override
    protected IModel<String> getPageTitle() {
        return new ResourceModel("p.placeEdit.caption");
    }
}

/*
public class ChoicePage extends BasePage
{
    public ChoicePage()
    {
        modelsMap.put("AUDI", Arrays.asList("A4", "A6", "TT"));
        modelsMap.put("CADILLAC", Arrays.asList("CTS", "DTS", "ESCALADE", "SRX", "DEVILLE"));
        modelsMap.put("FORD", Arrays.asList("CROWN", "ESCAPE", "EXPEDITION", "EXPLORER", "F-150"));

        IModel<List<? extends String>> makeChoices = new AbstractReadOnlyModel<List<? extends String>>()
        {
            @Override
            public List<String> getObject()
            {
                return new ArrayList<String>(modelsMap.keySet());
            }

        };

        IModel<List<? extends String>> modelChoices = new AbstractReadOnlyModel<List<? extends String>>()
        {
            @Override
            public List<String> getObject()
            {
                List<String> models = modelsMap.get(selectedMake);
                if (models == null)
                {
                    models = Collections.emptyList();
                }
                return models;
            }

        };

        Form<?> form = new Form("form");
        add(form);

        final DropDownChoice<String> makes = new DropDownChoice<String>("makes",
                new PropertyModel<String>(this, "selectedMake"), makeChoices);

        final DropDownChoice<String> models = new DropDownChoice<String>("models",
                new Model<String>(), modelChoices);
        models.setOutputMarkupId(true);

        form.add(makes);
        form.add(models);

        makes.add(new AjaxFormComponentUpdatingBehavior("change")
        {
            @Override
            protected void onUpdate(AjaxRequestTarget target)
            {
                target.add(models);
            }
        });
    }
}*/
