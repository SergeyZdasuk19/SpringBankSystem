package bankService.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "creditCard")
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    private String number;
    private String date;
    private int cvc;
    private int amount;

    public CreditCard() {

    }
}
