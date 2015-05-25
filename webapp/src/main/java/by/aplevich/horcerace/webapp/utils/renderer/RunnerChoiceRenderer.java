package by.aplevich.horcerace.webapp.utils.renderer;

import by.aplevich.horcerace.datamodel.Runner;
import org.apache.wicket.markup.html.form.IChoiceRenderer;

public class RunnerChoiceRenderer implements IChoiceRenderer<Runner> {

    public static RunnerChoiceRenderer INSTANCE = new RunnerChoiceRenderer();

    private RunnerChoiceRenderer() {
        super();
    }

    @Override
    public Object getDisplayValue(Runner runner) {
        return runner.getRunner();
    }

    @Override
    public String getIdValue(Runner runner, int index) {
        return runner.getId().toString();
    }
}