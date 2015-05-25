package by.aplevich.horcerace.webapp.utils.renderer;

import by.aplevich.horcerace.datamodel.enums.BetType;
import org.apache.wicket.markup.html.form.IChoiceRenderer;

public class TypeChoiceRenderer implements IChoiceRenderer<BetType> {
    public static TypeChoiceRenderer INSTANCE = new TypeChoiceRenderer();

    private TypeChoiceRenderer() {
        super();
    }

    @Override
    public Object getDisplayValue(BetType type) {
        return type.name();
    }

    @Override
    public String getIdValue(BetType type, int index) {
        return type.getValue().toString();
    }
}