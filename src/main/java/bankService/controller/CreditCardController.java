package bankService.controller;

import bankService.domain.Credit;
import bankService.domain.CreditCard;
import bankService.domain.Deposit;
import bankService.domain.PaymentAccount;
import bankService.repos.CreditCardRepo;
import bankService.repos.CreditRepo;
import bankService.repos.DepositRepo;
import bankService.repos.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
public class CreditCardController {
    @Autowired
    private CreditCardRepo creditCardRepo;
    @Autowired
    private PaymentRepo paymentRepo;
    @Autowired
    private CreditRepo creditRepo;
    @Autowired
    private DepositRepo depositRepo;

    @PostMapping("/creditCard/addAmountOnPaymentAccount/{id}")
    public String addCreditCardForAccount(Model model,
                                          @RequestParam Integer amount,
                                          @RequestParam String number,
                                          @RequestParam Integer cvc,
                                          @PathVariable("id") PaymentAccount paymentAccount) {
        CreditCard card = creditCardRepo.findByNumberAndCvc(number, cvc);
        card.setAmount(card.getAmount() - amount);
        creditCardRepo.save(card);
        paymentAccount.setAmount(paymentAccount.getAmount() + amount);
        paymentRepo.save(paymentAccount);
        return "redirect:/main";
    }

    @PostMapping("/creditCard/addAmountOnCredit/{id}")
    public String addCreditCardForCredit(Model model,
                                         @RequestParam Integer amount,
                                         @RequestParam String number,
                                         @RequestParam Integer cvc,
                                         @PathVariable("id") Credit credit) {
        CreditCard card = creditCardRepo.findByNumberAndCvc(number, cvc);
        if (credit.getWage() > amount) {
            card.setAmount(card.getAmount() - amount);
            credit.setWage(credit.getWage() - amount);
        } else {
            card.setAmount(card.getAmount() - credit.getWage());
            credit.setWage(0);
            credit.setStatus("Погашен");
        }
        creditCardRepo.save(card);
        creditRepo.save(credit);
        return "redirect:/main";
    }

    @PostMapping("/creditCard/addAmountOnDeposit/{id}")
    public String addCreditCardForDeposit(Model model,
                                          @RequestParam Integer amount,
                                          @RequestParam String number,
                                          @RequestParam Integer cvc,
                                          @PathVariable("id") Deposit deposit) {
        CreditCard card = creditCardRepo.findByNumberAndCvc(number, cvc);
        card.setAmount(card.getAmount() - amount);
        creditCardRepo.save(card);
        deposit.setWage(deposit.getWage().add(BigDecimal.valueOf(amount)));
        depositRepo.save(deposit);
        return "redirect:/main";
    }
}
