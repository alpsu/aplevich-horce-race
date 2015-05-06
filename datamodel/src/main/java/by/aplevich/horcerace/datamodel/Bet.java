package by.aplevich.horcerace.datamodel;

import by.aplevich.horcerace.datamodel.enums.BetType;
import by.aplevich.horcerace.datamodel.enums.Currency;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Bet details
 */
@Entity
public class Bet extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private BetType type;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Runner.class)
    @NotNull
    private Runner runner;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserAccount.class)
    @NotNull
    private UserAccount userAccount;

    @Column
    @NotNull
    @Max(value = 999999999)
    @Min(value = 0)
    private BigDecimal sum;

    @Column
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private Currency currency;

    @Override
    public Long getId() {
        return id;
    }

    public BetType getType() {
        return type;
    }

    public void setType(BetType type) {
        this.type = type;
    }

    public Runner getRunner() {
        return runner;
    }

    public void setRunner(Runner runner) {
        this.runner = runner;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount user) {
        this.userAccount = user;
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
                ", runner=" + runner +
                ", user=" + userAccount +
                ", sum=" + sum +
                '}';
    }
}