package syntax.errors.groupwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import syntax.errors.groupwork.model.Borrower;
import syntax.errors.groupwork.model.Rental;
import syntax.errors.groupwork.service.RentalService;
import syntax.errors.groupwork.repository.BorrowerRepository;

import java.util.Optional;

@Controller
@RequestMapping("/borrowers")
public class BorrowerController {

    @Autowired
    private BorrowerRepository borrowerRepository;

    @Autowired
    private RentalService rentalService;
    //manages the borrowers
    @GetMapping("/manage")
    public String showBorrowerManagement(Model model) {
        model.addAttribute("borrowers", borrowerRepository.findAll());
        model.addAttribute("borrower", new Borrower());
        return "borrower_management";
    }
    
    @PostMapping("/save")
    public String saveBorrower(@ModelAttribute("borrower") Borrower borrower) {
        borrowerRepository.save(borrower);
        return "redirect:/borrowers/manage";
    }

    @GetMapping("/edit/{id}")
    public String editBorrower(@PathVariable("id") int id, Model model) {
        Optional<Borrower> borrowerOpt = borrowerRepository.findById(id);
        if (borrowerOpt.isPresent()) {
            model.addAttribute("borrower", borrowerOpt.get());
            return "borrower_management";
        } else {
            return "redirect:/borrowers/manage";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteBorrower(@PathVariable("id") int id) {
        borrowerRepository.deleteById(id);
        return "redirect:/borrowers/manage";
    }

    @GetMapping("/{id}/history")
    public String viewRentalHistory(@PathVariable("id") int id, Model model) {
        Optional<Borrower> borrowerOpt = borrowerRepository.findById(id);
        if (borrowerOpt.isPresent()) {
            Borrower borrower = borrowerOpt.get();
            model.addAttribute("borrower", borrower);
            model.addAttribute("rentals", rentalService.getRentalsByBorrowerId(id));  // Fix here: using 'rentals'
            return "rental_history";
        } else {
            return "redirect:/borrowers/manage";
        }
    }
}
