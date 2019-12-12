package bankService.repos;

import bankService.domain.CreditCard;
import org.springframework.data.repository.CrudRepository;

public interface CreditCardRepo extends CrudRepository<CreditCard, Long> {
    CreditCard findByNumberAndCvc(String number, Integer cvc);
}
