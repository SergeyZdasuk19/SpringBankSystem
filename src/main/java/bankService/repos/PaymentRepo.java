package bankService.repos;

import bankService.domain.PaymentAccount;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PaymentRepo extends CrudRepository<PaymentAccount, Long> {
    List<PaymentAccount> findByUserId(long id);
}
