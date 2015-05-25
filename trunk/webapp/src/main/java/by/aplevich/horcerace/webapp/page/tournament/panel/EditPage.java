package by.aplevich.horcerace.webapp.page.tournament.panel;

import by.aplevich.horcerace.datamodel.*;
import by.aplevich.horcerace.services.*;
import by.aplevich.horcerace.webapp.page.BaseLayout;
import by.aplevich.horcerace.webapp.page.Place.PlaceEditPage;
import by.aplevich.horcerace.webapp.page.horse.HorceEditPage;
import by.aplevich.horcerace.webapp.page.jockey.JockeyEditPage;
import by.aplevich.horcerace.webapp.page.race.RaceEditPage;
import by.aplevich.horcerace.webapp.page.runner.RunnersEditPage;
import by.aplevich.horcerace.webapp.utils.renderer.*;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow.PageCreator;
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

@AuthorizeInstantiation({"ADMIN"})
public class EditPage extends BaseLayout {
    @Inject
    private PlaceService placeService;
    @Inject
    private RaceService raceService;
    @Inject
    private HorceService horceService;
    @Inject
    private JockeyService jockeyService;
    @Inject
    private RunnerService runnerService;

    private Place place;
    private Race race;
    private Horce horce;
    private Jockey jockey;
    private Runner runner;
    private PageCreator pageCreator;

    private IModel<Place> ddModelPlace = new PropertyModel<Place>(this, "place");
    private IModel<Race> ddModelRace = new PropertyModel<Race>(this, "race");
    private IModel<Runner> ddModelRunner = new PropertyModel<Runner>(this, "runner");
    private IModel<Horce> ddModelHorce = new PropertyModel<Horce>(this, "horce");
    private IModel<Jockey> ddModelJockey = new PropertyModel<Jockey>(this, "jockey");

    IModel<List<? extends Race>> races = new AbstractReadOnlyModel<List<? extends Race>>() {
        @Override
        public List<Race> getObject() {
            if (place == null) {
                return Collections.emptyList();
            }
            return raceService.getAllRacesWithPlaceByPlace(place);
        }
    };

    IModel<List<? extends Runner>> runners = new AbstractReadOnlyModel<List<? extends Runner>>() {
        @Override
        public List<Runner> getObject() {
            if ((place == null) || (race == null)) {
                return Collections.emptyList();
            }
            return runnerService.getAllRunnerByRace(race);
        }
    };

    public EditPage() {
        super();
    }

    public EditPage(PageCreator pageCreator) {
        super();
        this.pageCreator = pageCreator;
    }

    public EditPage(String id, IModel<?> model) {
        super();
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        Form form = new Form("f1") {
            @Override
            protected void onSubmit() {
                super.onSubmit();
                setResponsePage(pageCreator.createPage());
            }
        };
        form.setOutputMarkupId(true);

        // Block Place
        DropDownChoice<Place> ddPlace = new DropDownChoice<Place>("place", ddModelPlace, placeService.getAllPlaces(Place_.name, true, 0, 0), PlaceChoiceRenderer.INSTANCE);
        form.add(ddPlace);
        ddPlace.add(new AjaxFormComponentUpdatingBehavior("change") {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                target.add(form);
            }
        });

        Link<String> editPlaceLink = new Link<String>("editPlace") {
            @Override
            public void onClick() {
                setResponsePage(new PlaceEditPage(place, new PageCreator() {
                    @Override
                    public Page createPage() {
                        return new EditPage();
                    }
                }));
            }
        };
        form.add(editPlaceLink);

        Link<String> newPlaceLink = new Link<String>("newPlace") {
            @Override
            public void onClick() {
                setResponsePage(new PlaceEditPage(new Place(), new PageCreator() {
                    @Override
                    public Page createPage() {
                        return new EditPage();
                    }
                }));
            }
        };
        form.add(newPlaceLink);

        // Block Race
        DropDownChoice<Race> ddRace = new DropDownChoice<Race>("race", ddModelRace, races, RaceChoiceRenderer.INSTANCE);
        form.add(ddRace);
        ddRace.add(new AjaxFormComponentUpdatingBehavior("change") {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                target.add(form);
            }
        });

        Link<String> editRaceLink = new Link<String>("editRace") {
            @Override
            public void onClick() {
                setResponsePage(new RaceEditPage(race, new PageCreator() {
                    @Override
                    public Page createPage() {
                        return new EditPage();
                    }
                }));
            }
        };
        form.add(editRaceLink);

        Link<String> newRaceLink = new Link<String>("newRace") {
            @Override
            public void onClick() {
                setResponsePage(new RaceEditPage(new Race(), new PageCreator() {
                    @Override
                    public Page createPage() {
                        return new EditPage();
                    }
                }));
            }
        };
        form.add(newRaceLink);

        //Block Runner
        DropDownChoice<Runner> ddRunner = new DropDownChoice<Runner>("runner", ddModelRunner, runners, RunnerChoiceRenderer.INSTANCE);
        form.add(ddRunner);
        ddRunner.add(new AjaxFormComponentUpdatingBehavior("change") {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                target.add(form);
            }
        });

        Link<String> editRunnerLink = new Link<String>("editRunner") {
            @Override
            public void onClick() {
                setResponsePage(new RunnersEditPage(runner, new PageCreator() {
                    @Override
                    public Page createPage() {
                        return new EditPage();
                    }
                }));
            }
        };
        form.add(editRunnerLink);

        Link<String> newRunnerLink = new Link<String>("newRunner") {
            @Override
            public void onClick() {
                setResponsePage(new RunnersEditPage(new Runner(), new PageCreator() {
                    @Override
                    public Page createPage() {
                        return new EditPage();
                    }
                }));
            }
        };
        form.add(newRunnerLink);

        //Block Horce
        DropDownChoice<Horce> ddHorce = new DropDownChoice<Horce>("horce", ddModelHorce, horceService.getAllHorces(), HorceChoiceRenderer.INSTANCE);
        form.add(ddHorce);
        ddHorce.add(new AjaxFormComponentUpdatingBehavior("change") {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                target.add(form);////
            }
        });

        Link<String> editHorceLink = new Link<String>("editHorce") {
            @Override
            public void onClick() {
                setResponsePage(new HorceEditPage(horce, new PageCreator() {
                    @Override
                    public Page createPage() {
                        return new EditPage();
                    }
                }));
            }
        };
        form.add(editHorceLink);

        Link<String> newHorceLink = new Link<String>("newHorce") {
            @Override
            public void onClick() {
                setResponsePage(new HorceEditPage(new Horce(), new PageCreator() {
                    @Override
                    public Page createPage() {
                        return new EditPage();
                    }
                }));
            }
        };
        form.add(newHorceLink);

        //Block Jockey
        DropDownChoice<Jockey> ddJockey = new DropDownChoice<Jockey>("jockey", ddModelJockey, jockeyService.getAllJockeys(), JockeyChoiceRenderer.INSTANCE);
        form.add(ddJockey);
        ddJockey.add(new AjaxFormComponentUpdatingBehavior("change") {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                target.add(form);////
            }
        });

        Link<String> editJockeyLink = new Link<String>("editJockey") {
            @Override
            public void onClick() {
                setResponsePage(new JockeyEditPage(jockey, new PageCreator() {
                    @Override
                    public Page createPage() {
                        return new EditPage();
                    }
                }));
            }
        };
        form.add(editJockeyLink);

        Link<String> newJockeyLink = new Link<String>("newJockey") {
            @Override
            public void onClick() {
                setResponsePage(new JockeyEditPage(new Jockey(), new PageCreator() {
                    @Override
                    public Page createPage() {
                        return new EditPage();
                    }
                }));
            }
        };
        form.add(newJockeyLink);

        // add Form
        add(form);
    }

    @Override
    protected IModel<String> getPageTitle() {
        return new ResourceModel("p.placeEdit.caption");
    }
}