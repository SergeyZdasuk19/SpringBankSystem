package bankService.repos;

import bankService.domain.Credit;
import org.springframework.data.repository.CrudRepository;

public interface CreditRepo extends CrudRepository<Credit, Long> {
    Credit findByUser(long id);
}
