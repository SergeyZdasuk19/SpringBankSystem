package bankService.controller;

import bankService.domain.PaymentAccount;
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

    @PostMapping("/paymentAccount/addRequest/{id}")
    public String addScore(@PathVariable("id") PaymentAccount paymentAccount) {
        paymentAccount.setStatus("Подтвердить заявку");
        paymentRepo.save(paymentAccount);
        return "redirect:/main";
    }
}
