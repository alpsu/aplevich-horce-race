package by.aplevich.horcerace.webapp.page.home;

import by.aplevich.horcerace.webapp.page.BaseLayout;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;

public class HomePage extends BaseLayout {

    @Override
    protected void onInitialize() {
        super.onInitialize();

    }

    @Override
    protected IModel<String> getPageTitle() {
        return new ResourceModel("p.home.title");
    }
}