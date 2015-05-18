package by.aplevich.horcerace.webapp.page.tournament.panel;

import by.aplevich.horcerace.webapp.app.BasicAuthenticationSession;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;

import java.util.Locale;

public class LanguagePanel extends Panel {

    public LanguagePanel(String id) {
        super(id);

        Form languageForm = new Form("languageForm");

        languageForm.add(createLocaleChangingButton("russianButton", "ru"));
        languageForm.add(createLocaleChangingButton("englishButton", "en"));

        add(languageForm);
    }

    private Button createLocaleChangingButton(final String buttonId, final String localeString) {
        return new Button(buttonId) {
            @Override
            public void onSubmit() {
                changeUserLocaleTo(localeString);
            }
        };
    }

    private void changeUserLocaleTo(String localeString) {
        BasicAuthenticationSession.get().setLocale(new Locale(localeString));
    }
}