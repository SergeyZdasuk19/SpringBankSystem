package bankService.controller;

import bankService.domain.Credit;
import bankService.domain.PaymentAccount;
import bankService.domain.User;
import bankService.repos.CreditRepo;
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
    @Autowired
    private CreditRepo creditRepo;

    @GetMapping("/")
    public String greeting() {
        return "Greeting.html";
    }

    @GetMapping("/main")
    public String main(@AuthenticationPrincipal User user,
                       Model model) {
        model.addAttribute("user", user);
        model.addAttribute("paymentAccounts", paymentRepo.findByUserId(user.getId()));
        model.addAttribute("credit", creditRepo.findByUserId(user.getId()));
        return "MainPage.html";
    }

    @GetMapping("/updateAll")
    public String update(@AuthenticationPrincipal User user,
                         Model model) {
        model.addAttribute("paymentAccounts", paymentRepo.findByUserId(user.getId()));
        model.addAttribute("credit", creditRepo.findByUserId(user.getId()));
        return "MainPage.html";
    }
}
