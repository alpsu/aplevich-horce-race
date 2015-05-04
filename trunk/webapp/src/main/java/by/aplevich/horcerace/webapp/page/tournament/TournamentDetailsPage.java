package by.aplevich.horcerace.webapp.page.tournament;

import by.aplevich.horcerace.datamodel.Place;
import by.aplevich.horcerace.webapp.page.BaseLayout;
import by.aplevich.horcerace.webapp.page.tournament.panel.TournamentPanel;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow.PageCreator;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;

public class TournamentDetailsPage extends BaseLayout{

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new TournamentPanel("panel-tournament", place));
    }

    @Override
    protected IModel<String> getPageTitle() {
        return new ResourceModel("p.tournamentpage.title");
    }

    private Place place;
    private PageCreator pageCreator;

    public TournamentDetailsPage(Place place, PageCreator pageCreator) {
        super();
        this.place = place;
        this.pageCreator = pageCreator;
    }
}
