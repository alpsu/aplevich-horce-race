package by.aplevich.horcerace.webapp.page.jockey;

import by.aplevich.horcerace.datamodel.Horce;
import by.aplevich.horcerace.datamodel.Jockey;
import by.aplevich.horcerace.services.JockeyService;
import by.aplevich.horcerace.webapp.page.BaseLayout;
import by.aplevich.horcerace.webapp.page.home.HomePage;
import org.apache.wicket.bean.validation.PropertyValidator;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;

import javax.inject.Inject;

public class JockeyEditPage  extends BaseLayout {

        @Inject
        private JockeyService jockeyService;

        public JockeyEditPage(final Jockey jockey) {
            super();
            Form<Jockey> form = new Form<>("form", new CompoundPropertyModel<Jockey>(jockey));

            final TextField<String> tffName = new TextField<>("fname");
            tffName.add(new PropertyValidator<String>());
            tffName.setLabel(new ResourceModel("p.jockeyEdit.fname"));
            form.add(tffName);

            final TextField<String> tflName = new TextField<>("lname");
            tflName.add(new PropertyValidator<String>());
            tflName.setLabel(new ResourceModel("p.jockeyEdit.lname"));
            form.add(tflName);

            form.add(new SubmitLink("sumbit-link") {
                @Override
                public void onSubmit() {
                    super.onSubmit();
                    jockeyService.saveOrUpdate(jockey);

                    HomePage page = new HomePage();
                    setResponsePage(page);
                }

                @Override
                public void onError() {

                    super.onError();
                }
            });

            add(form);
        }
    }