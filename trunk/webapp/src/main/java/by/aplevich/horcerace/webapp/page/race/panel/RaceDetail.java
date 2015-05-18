package by.aplevich.horcerace.webapp.page.race.panel;

import by.aplevich.horcerace.services.RaceService;
import by.aplevich.horcerace.services.RunnerService;
import by.aplevich.horcerace.webapp.page.runner.panel.RunnerListPanel;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

import javax.inject.Inject;

public class RaceDetail extends Panel {
    @Inject
    private RaceService raceService;

    private Long placeId;
    private Long raceId;

    public RaceDetail(String id, Long placeId, Long raceId) {
        super(id);
        this.placeId = placeId;
        this.raceId = raceId;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        WebMarkupContainer liContainer = new WebMarkupContainer("wrapper-li-race-detail-panel");
        add(liContainer);

        liContainer.add(new Label("startTime", raceService.get(raceId).getStart().toString()));
        liContainer.add(new Label("desc", raceService.get(raceId).getDescription()));
        liContainer.add(new Label("dist", raceService.get(raceId).getDistance()));
        liContainer.add(new Label("quant", raceService.get(raceId).getQuantity()));
        liContainer.add(new RunnerListPanel("runnerlist", placeId, raceId));
    }
}