package by.aplevich.horcerace.datamodel;

import by.aplevich.horcerace.datamodel.enums.BetType;
import by.aplevich.horcerace.datamodel.enums.Currency;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Bet details
 */
@Entity
public class Bet extends AbstractEntity {
    @Column
    @Enumerated(EnumType.ORDINAL)
    private BetType type;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Race.class)
    private Race race;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserAccount.class)
    private UserAccount user;

    @Column
    private BigDecimal sum;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private Currency currency;

    public BetType getType() {
        return type;
    }

    public void setType(BetType type) {
        this.type = type;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public UserAccount getUser() {
        return user;
    }

    public void setUser(UserAccount user) {
        this.user = user;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Bet{" +
                "currency=" + currency +
                ", id=" + getId() +
                ", type=" + type +
                ", race=" + race +
                ", user=" + user +
                ", sum=" + sum +
                '}';
    }
}