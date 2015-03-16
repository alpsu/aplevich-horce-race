package by.aplevich.horcerace.datamodel;

/**
 * List of roles in system
 */
public enum UserRole {
    CLIENT,
    ADMIN,
    BOOKIE;

    public String getValue() {
        return this.name();
    }
}
