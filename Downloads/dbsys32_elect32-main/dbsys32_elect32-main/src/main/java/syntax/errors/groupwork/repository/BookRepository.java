package syntax.errors.groupwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import syntax.errors.groupwork.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
