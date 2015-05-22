package by.aplevich.horcerace.webapp.page.tournament.panel;

import by.aplevich.horcerace.datamodel.*;
import by.aplevich.horcerace.services.PlaceService;
import by.aplevich.horcerace.services.RaceService;
import by.aplevich.horcerace.webapp.page.BaseLayout;
import by.aplevich.horcerace.webapp.page.Place.PlaceEditPage;
import by.aplevich.horcerace.webapp.utils.renderer.PlaceChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;

import javax.inject.Inject;

//@AuthorizeInstantiation({"ADMIN"})
public class EditPage extends BaseLayout {
    @Inject
    private PlaceService placeService;

    private Place place;

    private IModel<Place> dropdownModel = new PropertyModel<Place>(this, "place");
    private Horce horce;
    private Jockey jockey;
    private UserAccount userAccount;
    private Bet bet;
    private Race race;
    private Runner runner;

    @Inject
    private RaceService raceService;

    public EditPage() {
        super();

        Form form = new Form("f1"){
            @Override
            protected void onSubmit() {
                place = dropdownModel.getObject();
            }
        };

        DropDownChoice<Place> ddPlace = new DropDownChoice<Place>("place", dropdownModel,  placeService.getAllPlaces(Place_.name, true, 0, 0), PlaceChoiceRenderer.INSTANCE);
        ddPlace.setLabel(new ResourceModel("p.raceEdit.place"));
        form.add(ddPlace);

        Link<String> actionLink = new Link<String>("editplace") {
            @Override
            protected void onConfigure() {
                super.onConfigure();
                if (place == null) {
                    setEnabled(false);
                }
            }

            @Override
            public void onClick() {

                setResponsePage(new PlaceEditPage(place));
            }
        };
        form.add(actionLink);


        add(form);
        //add(actionLink.add(new Label("", new ResourceModel("edit"))));
    }

    public EditPage(String id, IModel<?> model) {
        super();
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

//        WebMarkupContainer liContainer2 = new WebMarkupContainer("edit");
//
//        add(liContainer2);
//
//        liContainer2.add(new Link("create-new-place-link") {
//            @Override
//            public void onClick() {
//                setResponsePage(new PlaceEditPage(new Place()));
//            }
//        });
//        liContainer2.add(new Link("edit-place-link") {
//            @Override
//            public void onClick() {
//                setResponsePage(new PlaceEditPage(place));
//            }
//        });
//
//        liContainer2.add(new Link("create-new-horce-link") {
//            @Override
//            public void onClick() {
//                setResponsePage(new HorceEditPage(new Horce()));
//            }
//        });
//        liContainer2.add(new Link("edit-horce-link") {
//            @Override
//            public void onClick() {
//                setResponsePage(new HorceEditPage(horce));
//            }
//        });
//
//        liContainer2.add(new Link("create-new-jockey-link") {
//            @Override
//            public void onClick() {
//                setResponsePage(new JockeyEditPage(new Jockey()));
//            }
//        });
//        liContainer2.add(new Link("edit-jockey-link") {
//            @Override
//            public void onClick() {
//                setResponsePage(new JockeyEditPage(jockey));
//            }
//        });
//
//        liContainer2.add(new Link("create-new-race-link") {
//            @Override
//            public void onClick() {
//                setResponsePage(new RaceEditPage(new Race()));
//            }
//        });
//        liContainer2.add(new Link("edit-race-link") {
//            @Override
//            public void onClick() {
//                setResponsePage(new RaceEditPage(race));
//            }
//        });

//        liContainer2.add(new Link("create-new-runner-link") {
//            @Override
//            public void onClick() {
//                setResponsePage(new RunnerEditPage(new Runner()));
//            }
//        });
//        liContainer2.add(new Link("edit-runner-link") {
//            @Override
//            public void onClick() {
//                setResponsePage(new RunnerEditPage(runner));
//            }
//        });

    }

    @Override
    protected IModel<String> getPageTitle() {
        return new ResourceModel("p.placeEdit.caption");
    }
}