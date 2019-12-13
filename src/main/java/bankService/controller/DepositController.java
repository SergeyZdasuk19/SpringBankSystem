package bankService.controller;

import bankService.domain.*;
import bankService.repos.DepositRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Controller
public class DepositController {
    @Autowired
    private DepositRepo depositRepo;


    @PostMapping("/deposit/sendRequest")
    public String addCredit(@AuthenticationPrincipal User user,
                            @RequestParam Currency currentCurrency,
                            @RequestParam Integer number,
                            @RequestParam String passportSeries,
                            @RequestParam int amountYears,
                            @RequestParam BigDecimal wage,
                            @RequestParam Integer course) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        instance.add(Calendar.YEAR, amountYears);
        Deposit deposit = new Deposit(user, currentCurrency, passportSeries, amountYears,
                getRealWage(wage, course, amountYears), course,
                "На оформлении", number, new SimpleDateFormat("dd.MM.yyyy").format(new Date()),
                new SimpleDateFormat("dd.MM.yyyy").format(instance.getTime()),
                new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
        depositRepo.save(deposit);
        return "redirect:/main";
    }

    @PostMapping("/deposit/activate/{id}")
    public String activatePaymentAccount(@PathVariable("id") Deposit deposit) {
        deposit.setStatus("Активный");
        depositRepo.save(deposit);
        return "redirect:/main";
    }

    public BigDecimal getRealWage(BigDecimal wage, Integer course, Integer amountYears) {
        return (wage.add(wage.multiply(BigDecimal.valueOf(course))
                .divide(BigDecimal.valueOf(100))))
                .multiply(BigDecimal.valueOf(amountYears))
                .setScale(2);
    }
}
