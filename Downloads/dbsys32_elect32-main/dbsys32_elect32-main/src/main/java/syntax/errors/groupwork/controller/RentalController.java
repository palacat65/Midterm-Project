package syntax.errors.groupwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import syntax.errors.groupwork.model.Book;
import syntax.errors.groupwork.model.Borrower;
import syntax.errors.groupwork.model.Rental;
import syntax.errors.groupwork.repository.BookRepository;
import syntax.errors.groupwork.repository.BorrowerRepository;
import syntax.errors.groupwork.service.RentalService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/rentals")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BorrowerRepository borrowerRepository;
    //Shows the Rental Management Page
    @GetMapping("/manage")
    public String showRentalManagement(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        model.addAttribute("borrowers", borrowerRepository.findAll());
        model.addAttribute("rentals", rentalService.getAllRentals());
        return "rental_management";
    }

    @PostMapping("/add")
    public String createRental(@RequestParam("bookId") int bookId,
                               @RequestParam("borrowerId") int borrowerId,
                               @RequestParam("dueDate") String dueDateStr,
                               Model model) {
        try {
            Optional<Book> bookOpt = bookRepository.findById(bookId);
            Optional<Borrower> borrowerOpt = borrowerRepository.findById(borrowerId);

            if (bookOpt.isPresent() && borrowerOpt.isPresent()) {
                Book book = bookOpt.get();
                if (!book.getStatus().equals("Available")) {
                    model.addAttribute("error", "The selected book is not available for rent.");
                    return "rental_management"; // Re-show rental management page with error
                }

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date dueDate = sdf.parse(dueDateStr);

                Rental rental = new Rental();
                rental.setBook(book);
                rental.setBorrower(borrowerOpt.get());
                rental.setBorrowDate(new Date());
                rental.setDueDate(dueDate);
                rental.setPenalty(0);

                rentalService.createRental(rental);

                // Update book status to 'Borrowed'
                book.setStatus("Borrowed");
                bookRepository.save(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error occurred while creating the rental. Please try again.");
            return "rental_management";
        }

        return "redirect:/rentals/manage";
    }

    @PostMapping("/update/{id}")
    public String updateRental(@PathVariable("id") int id, @RequestParam("dueDate") String dueDateStr) {
        Rental rental = rentalService.getRentalById(id);
        if (rental != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date dueDate = sdf.parse(dueDateStr);
                rental.setDueDate(dueDate);
                rentalService.updateRental(rental);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "redirect:/rentals/manage";
    }

    @PostMapping("/return/{id}")
    public String returnBook(@PathVariable("id") int id) {
        Rental rental = rentalService.getRentalById(id);
        if (rental != null && rental.getReturnDate() == null) {
            Date now = new Date();
            rental.setReturnDate(now);

            long diff = now.getTime() - rental.getDueDate().getTime();
            if (diff > 0) {
                long daysLate = diff / (1000 * 60 * 60 * 24);
                rental.setPenalty(daysLate * 5);
            } else {
                rental.setPenalty(0);
            }

            rentalService.returnBook(rental);

            Book book = rental.getBook();
            book.setStatus("Available");
            bookRepository.save(book);
        }

        return "redirect:/rentals/manage";
    }

    @GetMapping("/delete/{id}")
    public String deleteRental(@PathVariable("id") int id) {
        rentalService.deleteRental(id);
        return "redirect:/rentals/manage";
    }
    
    @GetMapping("/pay/{id}")
    public String payPenalty(@PathVariable("id") int id) {
        Rental rental = rentalService.getRentalById(id);
        if (rental != null) {
            rental.setPenalty(0);
            rentalService.updateRental(rental);
        }
        return "redirect:/borrowers/" + rental.getBorrower().getBorrowerID() + "/history";
    }

}
