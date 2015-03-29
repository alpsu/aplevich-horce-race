package by.aplevich.horcerace.datamodel.enums;

/**
 * List of used bets type
 */
public enum BetType {
    SINGLE,
    EW;

    public String getValue() {
        return this.name();
    }
}
