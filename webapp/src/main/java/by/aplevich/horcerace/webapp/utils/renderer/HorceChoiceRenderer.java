package by.aplevich.horcerace.webapp.utils.renderer;

import by.aplevich.horcerace.datamodel.Horce;
import org.apache.wicket.markup.html.form.IChoiceRenderer;

public class HorceChoiceRenderer implements IChoiceRenderer<Horce> {

    public static HorceChoiceRenderer INSTANCE = new HorceChoiceRenderer();

    private HorceChoiceRenderer() {
        super();
    }

    @Override
    public Object getDisplayValue(Horce horce) {
        return horce.getName();
    }

    @Override
    public String getIdValue(Horce horce, int index) {
        return horce.getId().toString();
    }
}
