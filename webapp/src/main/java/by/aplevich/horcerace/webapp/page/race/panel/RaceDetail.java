package by.aplevich.horcerace.webapp.page.race.panel;

import by.aplevich.horcerace.datamodel.Race;
import by.aplevich.horcerace.webapp.page.runner.panel.RunnerListPanel;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

public class RaceDetail extends Panel{
    private Race race;

    public RaceDetail (String id, Race race) {
        super(id);
        this.race = race;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        WebMarkupContainer liContainer = new WebMarkupContainer("wrapper-li-race-detail-panel");
        add(liContainer);

        liContainer.add(new Label("startTime", race.getStart().toString()));
        liContainer.add(new Label("desc", race.getDescription()));
        liContainer.add(new Label("dist", race.getDistance()));
        liContainer.add(new Label("quant", race.getQuantity()));
        liContainer.add(new RunnerListPanel("runnerlist"));
    }
}