package by.aplevich.horcerace.webapp.utils.renderer;

import by.aplevich.horcerace.datamodel.enums.UserRole;
import org.apache.wicket.markup.html.form.IChoiceRenderer;

public class RoleChoiceRenderer implements IChoiceRenderer<UserRole> {

    public static RoleChoiceRenderer INSTANCE = new RoleChoiceRenderer();

    private RoleChoiceRenderer() {
        super();
    }

    @Override
    public Object getDisplayValue(UserRole role) {
        return role.name();
    }

    @Override
    public String getIdValue(UserRole role, int index) {
        return role.getValue().toString();
    }
}
