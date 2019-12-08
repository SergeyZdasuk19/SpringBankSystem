package bankService.repos;

import bankService.domain.Credit;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CreditRepo extends CrudRepository<Credit, Long> {
    List<Credit> findByUserId(long id);
}
