package syntax.errors.groupwork.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Borrower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int borrowerID;

    private String name;
    private String email;

    @OneToMany(mappedBy = "borrower", cascade = CascadeType.ALL)
    private List<Rental> rentals;

    // Getters and Setters
    public int getBorrowerID() {
        return borrowerID;
    }

    public void setBorrowerID(int borrowerID) {
        this.borrowerID = borrowerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }
}
