package by.aplevich.horcerace.webapp.utils.renderer;

import by.aplevich.horcerace.datamodel.Jockey;
import org.apache.wicket.markup.html.form.IChoiceRenderer;

public class JockeyChoiceRenderer implements IChoiceRenderer<Jockey> {

    public static JockeyChoiceRenderer INSTANCE = new JockeyChoiceRenderer();

    private JockeyChoiceRenderer() {
        super();
    }

    @Override
    public Object getDisplayValue(Jockey jockey) {
        return jockey.getFname() + " " + jockey.getLname();
    }

    @Override
    public String getIdValue(Jockey jockey, int index) {
        return jockey.getId().toString();
    }
}