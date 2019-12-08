package bankService.controller;

import bankService.domain.Credit;
import bankService.domain.Currency;
import bankService.domain.PaymentAccount;
import bankService.domain.User;
import bankService.repos.CreditRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CreditController {
    @Autowired
    private CreditRepo creditRepo;

    @PostMapping("/credit/add")
    public String addCredit(@AuthenticationPrincipal User user,
                            @RequestParam Currency currentCurrency,
                            @RequestParam PaymentAccount paymentAccount,
                            @RequestParam String address,
                            @RequestParam String passportSeries,
                            @RequestParam String creditGoal,
                            @RequestParam Integer wage,
                            @RequestParam Integer course) {
        Credit credit = new Credit(user, paymentAccount, currentCurrency, address,
                passportSeries, creditGoal, wage, course, true);
        creditRepo.save(credit);
        return "redirect:/updateAll";
    }

    public Integer getRealWage(int wage,
                               int course) {
        return (wage * course) / 100;
    }
}
