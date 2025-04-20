package syntax.errors.groupwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import syntax.errors.groupwork.model.Borrower;
import syntax.errors.groupwork.model.Rental;
import syntax.errors.groupwork.repository.BorrowerRepository;
import syntax.errors.groupwork.repository.RentalRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BorrowerService {

    @Autowired
    private BorrowerRepository borrowerRepository;

    @Autowired
    private RentalRepository rentalRepository;

    public List<Borrower> getAllBorrowers() {
        return borrowerRepository.findAll();
    }

    public Borrower getBorrowerById(int id) {
        Optional<Borrower> b = borrowerRepository.findById(id);
        return b.orElse(null);
    }

    public void deleteBorrower(int id) {
        borrowerRepository.deleteById(id);
    }

    public void saveBorrower(Borrower b) {
        borrowerRepository.save(b);
    }

    public List<Rental> getRentalHistoryForBorrower(int borrowerId) {
    	return rentalRepository.findByBorrower_BorrowerID(borrowerId);

    }
}
