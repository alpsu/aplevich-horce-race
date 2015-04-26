package by.aplevich.horcerace.webapp.page;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;

public class HomePage extends BaseLayout {

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new BookmarkablePageLink<Void>("login-l", LoginPage.class));

        Link<Void> link = new Link<Void>("fr-l") {
            @Override
            public void onClick() {
                setResponsePage(new NextPage());
            }
        };
        link.add(AttributeModifier.append("class", "red"));
        add(link);
    }

    @Override
    protected IModel<String> getPageTitle() {
        return new ResourceModel("p.home.title");
    }
}