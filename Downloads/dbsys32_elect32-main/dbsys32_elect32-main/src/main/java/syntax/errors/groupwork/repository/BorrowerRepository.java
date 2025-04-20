package syntax.errors.groupwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import syntax.errors.groupwork.model.Borrower;

public interface BorrowerRepository extends JpaRepository<Borrower, Integer> {
}
