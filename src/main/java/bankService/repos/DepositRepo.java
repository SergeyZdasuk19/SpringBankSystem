package bankService.repos;

import bankService.domain.Deposit;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DepositRepo extends CrudRepository<Deposit, Long> {
    List<Deposit> findByUserId(long id);
}
