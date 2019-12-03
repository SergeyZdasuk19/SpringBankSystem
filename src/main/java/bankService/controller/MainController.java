package bankService.controller;

import bankService.domain.User;
import bankService.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public String greeting() {
        return "Greeting.html";
    }

    @GetMapping("/main")
    public String main(@AuthenticationPrincipal User user,
                       Model model) {
        model.addAttribute("user", user);
        return "MainPage.html";
    }
    @PostMapping("main")
    public String add(@AuthenticationPrincipal User user) {

        return "main";
    }

}
