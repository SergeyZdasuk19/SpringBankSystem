package bankService.controller;

import bankService.domain.Role;
import bankService.domain.User;
import bankService.repos.CreditRepo;
import bankService.repos.DepositRepo;
import bankService.repos.PaymentRepo;
import bankService.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PaymentRepo paymentRepo;
    @Autowired
    private CreditRepo creditRepo;
    @Autowired
    private DepositRepo depositRepo;

    @GetMapping("/")
    public String greeting() {
        return "Greeting.html";
    }

    @GetMapping("/main")
    public String main(@AuthenticationPrincipal User user,
                       Model model) {
        model.addAttribute("user", user);
        for (Role role : user.getRoles()) {
            switch (role) {
                case USER:
                    model.addAttribute("paymentAccounts", paymentRepo.findByUserId(user.getId()));
                    model.addAttribute("credit", creditRepo.findByUserId(user.getId()));
                    model.addAttribute("deposit", depositRepo.findByUserId(user.getId()));
                    break;
                case ADMIN:
                    model.addAttribute("paymentAccounts", paymentRepo.findAll());
                    model.addAttribute("credit", creditRepo.findAll());
                    model.addAttribute("deposit", depositRepo.findAll());
                    break;
            }
        }
        return "MainPage.html";
    }
}
