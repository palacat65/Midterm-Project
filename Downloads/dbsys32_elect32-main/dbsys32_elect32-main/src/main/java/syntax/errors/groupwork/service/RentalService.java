package syntax.errors.groupwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import syntax.errors.groupwork.model.Rental;
import syntax.errors.groupwork.repository.RentalRepository;

import java.util.List;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public Rental getRentalById(int id) {
        return rentalRepository.findById(id).orElse(null);
    }

    public void createRental(Rental rental) {
        rentalRepository.save(rental);
    }

    public void returnBook(Rental rental) {
        rentalRepository.save(rental);
    }

    public List<Rental> getRentalsByBorrowerId(int borrowerId) {
        return rentalRepository.findByBorrower_BorrowerID(borrowerId);
    }

    public void deleteRental(int id) {
        rentalRepository.deleteById(id);
    }

    public void updateRental(Rental rental) {
        rentalRepository.save(rental);
    }
}
