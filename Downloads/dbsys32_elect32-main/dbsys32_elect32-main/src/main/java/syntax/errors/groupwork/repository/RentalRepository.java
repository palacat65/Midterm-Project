package syntax.errors.groupwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import syntax.errors.groupwork.model.Rental;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Integer> {
    List<Rental> findByBorrower_BorrowerID(int borrowerId);
}
