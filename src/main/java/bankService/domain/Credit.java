package bankService.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "credit")
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_id")
    private PaymentAccount paymentAccount;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private String address;
    private String passportSeries;
    private String goal;
    private int amountYears;
    private int wage;
    private int course;
    private String status;

    public Credit() {

    }

    public Credit(User user,
                  int amountYears,
                  PaymentAccount paymentAccount,
                  Currency currency,
                  String address,
                  String passportSeries,
                  String goal,
                  int wage,
                  int course,
                  String status) {
        this.user = user;
        this.amountYears = amountYears;
        this.paymentAccount = paymentAccount;
        this.currency = currency;
        this.address = address;
        this.passportSeries = passportSeries;
        this.goal = goal;
        this.wage = wage;
        this.course = course;
        this.status = status;
    }

}
