package by.aplevich.horcerace.webapp.utils.renderer;

import by.aplevich.horcerace.datamodel.enums.Currency;
import org.apache.wicket.markup.html.form.IChoiceRenderer;

public class CurrencyChoiceRenderer implements IChoiceRenderer<Currency> {
    public static CurrencyChoiceRenderer INSTANCE = new CurrencyChoiceRenderer();

    private CurrencyChoiceRenderer() {
        super();
    }

    @Override
    public Object getDisplayValue(Currency currency) {
        return currency.name();
    }

    @Override
    public String getIdValue(Currency currency, int index) {
        return currency.getValue().toString();
    }
}