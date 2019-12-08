package bankService.repos;

import bankService.domain.PaymentAccount;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepo extends CrudRepository<PaymentAccount, Long> {
    PaymentAccount findById(long id);
}
