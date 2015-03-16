package by.aplevich.horcerace.datamodel;

/**
 * List of used currency
 */
public enum Currency {
    BYR,
    EUR,
    RUB,
    USD;

    public String getValue() {
        return this.name();
    }
}
