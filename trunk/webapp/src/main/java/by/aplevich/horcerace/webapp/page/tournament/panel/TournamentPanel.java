package by.aplevich.horcerace.webapp.page.tournament.panel;

import by.aplevich.horcerace.datamodel.*;
import by.aplevich.horcerace.webapp.page.Place.PlaceEditPage;
import by.aplevich.horcerace.webapp.page.bet.BetEditPage;
import by.aplevich.horcerace.webapp.page.horse.HorceEditPage;
import by.aplevich.horcerace.webapp.page.jockey.JockeyEditPage;
import by.aplevich.horcerace.webapp.page.race.RaceEditPage;
import by.aplevich.horcerace.webapp.page.runner.RunnerEditPage;
import by.aplevich.horcerace.webapp.page.user.UserEditPage;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class TournamentPanel extends Panel{

    private Place place;
    private Horce horce;
    private Jockey jockey;
    private UserAccount userAccount;
    private Bet bet;
    private Race race;
    private Runner runner;

    public TournamentPanel(String id, Place place) {
        super(id);
        this.place = place;
    }

    public TournamentPanel(String id, Place place, IModel<?> model) {
        super(id, model);
        this.place = place;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        WebMarkupContainer liContainer = new WebMarkupContainer("wrapper-li");
        add(liContainer);

        liContainer.add(new Label("name", new Model<String>(place.getName())));
        liContainer.add(new Link("create-new-place-link") {
            @Override
            public void onClick() {
                setResponsePage(new PlaceEditPage(new Place()));
            }
        });
        liContainer.add(new Link("edit-place-link") {
            @Override
            public void onClick() {
                setResponsePage(new PlaceEditPage(place));
            }
        });

        liContainer.add(new Link("create-new-horce-link") {
            @Override
            public void onClick() {
                setResponsePage(new HorceEditPage(new Horce()));
            }
        });
        liContainer.add(new Link("edit-horce-link") {
            @Override
            public void onClick() {
                setResponsePage(new HorceEditPage(horce));
            }
        });

        liContainer.add(new Link("create-new-jockey-link") {
            @Override
            public void onClick() {
                setResponsePage(new JockeyEditPage(new Jockey()));
            }
        });
        liContainer.add(new Link("edit-jockey-link") {
            @Override
            public void onClick() {
                setResponsePage(new JockeyEditPage(jockey));
            }
        });

        liContainer.add(new Link("create-new-user-link") {
            @Override
            public void onClick() {
                setResponsePage(new UserEditPage(new UserAccount()));
            }
        });
        liContainer.add(new Link("edit-user-link") {
            @Override
            public void onClick() {
                setResponsePage(new UserEditPage(userAccount));
            }
        });

        liContainer.add(new Link("create-new-race-link") {
            @Override
            public void onClick() {
                setResponsePage(new RaceEditPage(new Race()));
            }
        });
        liContainer.add(new Link("edit-race-link") {
            @Override
            public void onClick() {
                setResponsePage(new RaceEditPage(race));
            }
        });

        liContainer.add(new Link("create-new-runner-link") {
            @Override
            public void onClick() {
                setResponsePage(new RunnerEditPage(new Runner()));
            }
        });
        liContainer.add(new Link("edit-runner-link") {
            @Override
            public void onClick() {
                setResponsePage(new RunnerEditPage(runner));
            }
        });

        liContainer.add(new Link("create-new-bet-link") {
            @Override
            public void onClick() {
                setResponsePage(new BetEditPage(new Bet()));
            }
        });
        liContainer.add(new Link("edit-bet-link") {
            @Override
            public void onClick() {
                setResponsePage(new BetEditPage(bet));
            }
        });
    }
}
