package bankService.controller;

import bankService.domain.CreditCard;
import bankService.domain.PaymentAccount;
import bankService.repos.CreditCardRepo;
import bankService.repos.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CreditCardController {
    @Autowired
    private CreditCardRepo creditCardRepo;

    @Autowired
    private PaymentRepo paymentRepo;

    @PostMapping("/creditCard/addAmountOnPaymentAccount/{id}")
    public String addCreditCard(Model model,
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
}
