package by.aplevich.horcerace.webapp.page.tournament;

import by.aplevich.horcerace.webapp.page.BaseLayout;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;

public class HelpPage extends BaseLayout {
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