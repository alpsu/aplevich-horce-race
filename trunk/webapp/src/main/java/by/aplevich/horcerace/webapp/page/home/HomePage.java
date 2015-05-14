package by.aplevich.horcerace.webapp.page.home;

import by.aplevich.horcerace.datamodel.Place_;
import by.aplevich.horcerace.services.PlaceService;
import by.aplevich.horcerace.webapp.page.BaseLayout;
import by.aplevich.horcerace.webapp.page.tournament.TournamentDetailsPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;

import javax.inject.Inject;

public class HomePage extends BaseLayout {

    @Inject
    private PlaceService placeService;

    @Override
    protected void onInitialize() {
        super.onInitialize();
        setResponsePage(new TournamentDetailsPage(placeService.getAllPlaces(Place_.name, true, 0, 0).get(0)));
    }

    @Override
    protected IModel<String> getPageTitle() {
        return new ResourceModel("p.home.title");
    }
}