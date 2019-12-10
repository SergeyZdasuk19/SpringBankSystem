package bankService.controller;

import bankService.domain.Currency;
import bankService.domain.PaymentAccount;
import bankService.domain.User;
import bankService.repos.PaymentRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Controller
public class PaymentAccountController {
    @Autowired
    private PaymentRepo paymentRepo;

    @PostMapping("/paymentAccount/addRequest")
    public String addAccount(@AuthenticationPrincipal User user,
                             @RequestParam Currency currentCurrency) {
        PaymentAccount paymentAccountObj = new PaymentAccount(currentCurrency, user, getCode(currentCurrency), "На оформлении");
        paymentRepo.save(paymentAccountObj);
        return "redirect:/updateAll";
    }

    @PostMapping("/payment/addAmount/{id}")
    public String addAmount(@AuthenticationPrincipal User user,
                            @RequestParam Integer amount,
                            @PathVariable("id") PaymentAccount paymentAccount) {
        paymentAccount.setAmount(paymentAccount.getAmount() + amount);
        paymentRepo.save(paymentAccount);
        return "redirect:/updateAll";
    }

    public String getCode(Currency currency) {
        String code = new String(currency.getAuthority());
        switch (currency) {
            case BYN:
                code = code.concat("1010933").concat(String.valueOf(100000 + (int) (Math.random() * 100)));
                break;
            case EUR:
                code = code.concat("1010985").concat(String.valueOf(100000 + (int) (Math.random() * 100)));
                break;
            case USD:
                code = code.concat("1010840").concat(String.valueOf(100000 + (int) (Math.random() * 100)));
                break;
        }
        return code;
    }
}
