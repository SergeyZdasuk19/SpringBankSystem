package bankService.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "paymentAccount")
public class PaymentAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String number;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private int amount;
    private String status;

    public PaymentAccount() {

    }

    public PaymentAccount(Currency currency, User user, String number, String status) {
        this.currency = currency;
        this.user = user;
        this.number = number;
        this.status = status;
    }
}
