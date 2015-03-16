package by.aplevich.horcerace.datamodel;

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
