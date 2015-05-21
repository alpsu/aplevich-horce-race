package by.aplevich.horcerace.webapp.page.tournament;

import by.aplevich.horcerace.datamodel.Place;
import by.aplevich.horcerace.webapp.page.BaseLayout;
import by.aplevich.horcerace.webapp.page.tournament.panel.TournamentPanel;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow.PageCreator;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;

public class HelpPage extends BaseLayout{
    public HelpPage() {
        super();
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
    }

    @Override
    protected IModel<String> getPageTitle() {
        return new ResourceModel("p.helpPage.text");
    }
}