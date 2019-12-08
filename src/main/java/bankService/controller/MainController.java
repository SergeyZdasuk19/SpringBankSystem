package bankService.controller;

import bankService.domain.PaymentAccount;
import bankService.domain.User;
import bankService.repos.PaymentRepo;
import bankService.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PaymentRepo paymentRepo;

    @GetMapping("/")
    public String greeting() {
        return "Greeting.html";
    }

    @GetMapping("/main")
    public String main(@AuthenticationPrincipal User user,
                       Model model) {
        Iterable<PaymentAccount> paymentAccount = paymentRepo.findAll();
        model.addAttribute("paymentAccounts", paymentAccount);
        model.addAttribute("user", user);
        return "MainPage.html";
    }

    @GetMapping("/updateAll")
    public String update(Model model) {
        Iterable<PaymentAccount> paymentAccount = paymentRepo.findAll();
        model.addAttribute("paymentAccounts", paymentAccount);
        return "MainPage.html";
    }
}
