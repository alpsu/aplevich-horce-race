package by.aplevich.horcerace.datamodel;

import by.aplevich.horcerace.datamodel.enums.BetType;
import by.aplevich.horcerace.datamodel.enums.Currency;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Bet details
 */
@Entity
public class Bet {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private BetType type;
    @Column
    private Race race;
    @Column
    private User user;
    @Column
    private BigDecimal sum;
    @Column
    private Currency currency;

    public Long getId() {
        return id;
    }

//    public void setId(Long id) {
//        this.id = id;
//    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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

}
