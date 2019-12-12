package bankService.controller;

import bankService.domain.Credit;
import bankService.domain.Currency;
import bankService.domain.PaymentAccount;
import bankService.domain.User;
import bankService.repos.CreditRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CreditController {
    @Autowired
    private CreditRepo creditRepo;

    @PostMapping("/credit/sendRequest")
    public String addCredit(@AuthenticationPrincipal User user,
                             @RequestParam Integer years,
                             @RequestParam Currency currentCurrency,
                             @RequestParam PaymentAccount paymentAccount,
                             @RequestParam String address,
                             @RequestParam String passportSeries,
                             @RequestParam String creditGoal,
                             @RequestParam Integer wage,
                             @RequestParam Integer course) {
        Credit credit = new Credit(user, years, paymentAccount, currentCurrency, address,
                passportSeries, creditGoal, getRealWage(wage, course), course, "На оформлении");
        creditRepo.save(credit);
        return "redirect:/main";
    }

    @PostMapping("/credit/activate/{id}")
    public String activatePaymentAccount(@PathVariable("id") Credit credit) {
        credit.setStatus("Активный");
        creditRepo.save(credit);
        return "redirect:/main";
    }


    public Integer getRealWage(int wage,
                               int course) {
        return wage + (wage * course) / 100;
    }
}
