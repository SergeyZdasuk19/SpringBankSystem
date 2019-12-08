package bankService.domain;

import javax.persistence.*;

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
    private boolean status;

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
                  boolean status) {
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

    public int getAmountYears() {
        return amountYears;
    }

    public void setAmountYears(int amountYears) {
        this.amountYears = amountYears;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public PaymentAccount getPaymentAccount() {
        return paymentAccount;
    }

    public void setPaymentAccount(PaymentAccount paymentAccount) {
        this.paymentAccount = paymentAccount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public int getWage() {
        return wage;
    }

    public void setWage(int wage) {
        this.wage = wage;
    }
}
