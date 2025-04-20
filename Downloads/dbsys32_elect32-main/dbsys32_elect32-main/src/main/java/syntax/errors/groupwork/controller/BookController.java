package syntax.errors.groupwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import syntax.errors.groupwork.model.Book;
import syntax.errors.groupwork.service.BookService;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // Home page
    @GetMapping
    public String showHomePage() {
        return "index";
    }

    // Book Management Page
    @GetMapping("/manage")
    public String showBookManagement(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("book", new Book()); // Empty form for create
        return "book_management";
    }

    // Create or Update Book
    @PostMapping("/save")
    public String saveBook(@ModelAttribute("book") Book book) {
        bookService.saveBook(book);
        return "redirect:/books/manage";
    }

    // Edit Book
    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable("id") int id, Model model) {
        Book book = bookService.getBookById(id);
        if (book != null) {
            model.addAttribute("book", book); // Populate form
            model.addAttribute("books", bookService.getAllBooks()); // Show book list
            return "book_management";
        } else {
            return "redirect:/books/manage";
        }
    }

    // Delete Book
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookService.deleteBook(id);
        return "redirect:/books/manage";
    }
}
