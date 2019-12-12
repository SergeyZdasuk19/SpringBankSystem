package bankService.controller;

import bankService.domain.Credit;
import bankService.domain.PaymentAccount;
import bankService.repos.CreditRepo;
import bankService.repos.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@PreAuthorize("hasAuthority('ADMIN')")
@Controller
public class AdminPaymentController {
    @Autowired
    private PaymentRepo paymentRepo;
    @Autowired
    private CreditRepo creditRepo;

    @PostMapping("/paymentAccount/addRequest/{id}")
    public String addPaymentAccountScore(@PathVariable("id") PaymentAccount paymentAccount) {
        paymentAccount.setStatus("Подтвердить заявку");
        paymentRepo.save(paymentAccount);
        return "redirect:/main";
    }

    @PostMapping("/credit/addRequest/{id}")
    public String addCreditScore(@PathVariable("id") Credit credit) {
        credit.setStatus("Подтвердить заявку");
        creditRepo.save(credit);
        return "redirect:/main";
    }
}
