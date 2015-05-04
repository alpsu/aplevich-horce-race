package by.aplevich.horcerace.webapp.page.panel;

import by.aplevich.horcerace.datamodel.Place;
import by.aplevich.horcerace.webapp.page.home.HomePage;
import by.aplevich.horcerace.webapp.page.tournament.TournamentDetailsPage;
import org.apache.wicket.Page;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow.PageCreator;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

public class PlacePanel extends Panel {
    private Place place;

    public PlacePanel(String id, Place place) {
        super(id);
        this.place = place;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        WebMarkupContainer liContainer = new WebMarkupContainer("wrapper-li");
        WebMarkupContainer linkContainer = new WebMarkupContainer("link");
        add(liContainer);

        liContainer.add(linkContainer);
        //liContainer.add(new Label("name", new Model<>(place.getName())));
        linkContainer.add(new Link<String>("name", new Model<>(place.getName())) {
            @Override
            public void onClick() {
                setResponsePage(new TournamentDetailsPage(place, new PageCreator() {

                    @Override
                    public Page createPage() {
                        return new HomePage();
                    }
                }));
            }
        });
    }
}
