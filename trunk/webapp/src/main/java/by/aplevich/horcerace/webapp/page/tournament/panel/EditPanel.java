package by.aplevich.horcerace.webapp.page.tournament.panel;

import by.aplevich.horcerace.datamodel.*;
import by.aplevich.horcerace.services.RaceService;
import by.aplevich.horcerace.webapp.app.BasicAuthenticationSession;
import by.aplevich.horcerace.webapp.page.Place.PlaceEditPage;
import by.aplevich.horcerace.webapp.page.bet.BetEditPage;
import by.aplevich.horcerace.webapp.page.horse.HorceEditPage;
import by.aplevich.horcerace.webapp.page.jockey.JockeyEditPage;
import by.aplevich.horcerace.webapp.page.race.RaceEditPage;
import by.aplevich.horcerace.webapp.page.runner.RunnerEditPage;
import by.aplevich.horcerace.webapp.page.user.UserEditPage;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import javax.inject.Inject;

//@AuthorizeInstantiation({"ADMIN"})
public class EditPanel extends Panel {
    private Place place;
    private Horce horce;
    private Jockey jockey;
    private UserAccount userAccount;
    private Bet bet;
    private Race race;
    private Runner runner;

    @Inject
    private RaceService raceService;

    public EditPanel(String id, Place place) {
        super(id);
        this.place = place;
    }

    public EditPanel(String id, IModel<?> model) {
        super(id, model);
        this.place = place;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        WebMarkupContainer liContainer2 = new WebMarkupContainer("edit") {

            @Override
            protected void onBeforeRender() {
                super.onBeforeRender();
                Roles roles = BasicAuthenticationSession.get().getRoles();
                if (roles != null && roles.hasRole("ADMIN")) {
                        setVisible(true);
                } else {
                    setVisible(false);
                }
            }
        };

        add(liContainer2);

        liContainer2.add(new Link("create-new-place-link") {
            @Override
            public void onClick() {
                setResponsePage(new PlaceEditPage(new Place()));
            }
        });
        liContainer2.add(new Link("edit-place-link") {
            @Override
            public void onClick() {
                setResponsePage(new PlaceEditPage(place));
            }
        });

        liContainer2.add(new Link("create-new-horce-link") {
            @Override
            public void onClick() {
                setResponsePage(new HorceEditPage(new Horce()));
            }
        });
        liContainer2.add(new Link("edit-horce-link") {
            @Override
            public void onClick() {
                setResponsePage(new HorceEditPage(horce));
            }
        });

        liContainer2.add(new Link("create-new-jockey-link") {
            @Override
            public void onClick() {
                setResponsePage(new JockeyEditPage(new Jockey()));
            }
        });
        liContainer2.add(new Link("edit-jockey-link") {
            @Override
            public void onClick() {
                setResponsePage(new JockeyEditPage(jockey));
            }
        });

        liContainer2.add(new Link("create-new-race-link") {
            @Override
            public void onClick() {
                setResponsePage(new RaceEditPage(new Race()));
            }
        });
        liContainer2.add(new Link("edit-race-link") {
            @Override
            public void onClick() {
                setResponsePage(new RaceEditPage(race));
            }
        });

        liContainer2.add(new Link("create-new-runner-link") {
            @Override
            public void onClick() {
                setResponsePage(new RunnerEditPage(new Runner()));
            }
        });
        liContainer2.add(new Link("edit-runner-link") {
            @Override
            public void onClick() {
                setResponsePage(new RunnerEditPage(runner));
            }
        });

        liContainer2.add(new Link("create-new-bet-link") {
            @Override
            public void onClick() {
                setResponsePage(new BetEditPage(new Bet()));
            }
        });
        liContainer2.add(new Link("edit-bet-link") {
            @Override
            public void onClick() {
                setResponsePage(new BetEditPage(bet));
            }
        });
    }
}