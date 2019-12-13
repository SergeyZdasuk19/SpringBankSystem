package bankService.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "deposit")
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    private Currency currency;
    private String passportSeries;
    private int amountYears;
    private BigDecimal wage;
    private int course;
    private String status;
    private int number;
    private String startDate;
    private String endDate;
    private String nowDate;

    public Deposit() {

    }

    public Deposit(User user,
                   Currency currency,
                   String passportSeries,
                   int amountYears,
                   BigDecimal wage,
                   int course,
                   String status,
                   int number,
                   String startDate,
                   String endDate,
                   String nowDate) {
        this.user = user;
        this.currency = currency;
        this.passportSeries = passportSeries;
        this.amountYears = amountYears;
        this.wage = wage;
        this.course = course;
        this.status = status;
        this.number = number;
        this.startDate = startDate;
        this.endDate = endDate;
        this.nowDate = nowDate;
    }

}
