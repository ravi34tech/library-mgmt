package com.ravi.library.bookmgmt.controller;

import com.ravi.library.bookmgmt.dto.BookResponse;
import com.ravi.library.bookmgmt.entity.BookDetails;
import com.ravi.library.bookmgmt.service.BookDetailsService;
import com.ravi.library.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookDetailsService bookDetailsService;

    @GetMapping("/{id}")
    public BookDetails find(@PathVariable Long id){
        return bookDetailsService.findBook(id);
    }

    @GetMapping
    public BookResponse findAllBooks(){
        BookResponse response = new BookResponse();
        response.setBookList(bookDetailsService.findAllBooks());
        return response;
    }

    @GetMapping("/name/{bookName}")
    public BookResponse findBooksByBookName(@PathVariable(required = true) String bookName){
        BookResponse response = new BookResponse();
        response.setBookList(bookDetailsService.findBooksByBookName(bookName));
        return response;
    }

    @GetMapping("/author/{authorName}")
    public BookResponse findBooksByAuthorName(@PathVariable(required = true) String authorName){
        BookResponse response = new BookResponse();
        response.setBookList(bookDetailsService.findBooksByAuthor(authorName));
        return response;
    }

    @GetMapping("/country")
    public BookResponse findCountryWiseBooks(@RequestParam(name = "country",required = true) String country){
        BookResponse response = new BookResponse();
        response.setBookList(bookDetailsService.findByCountryContainingIgnoreCase(country));
        return response;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        bookDetailsService.deleteBook(id);
        return "Deleted Book Successfully...";
    }

    @PostMapping
    public BookResponse save(@RequestBody BookDetails details){
        BookResponse response = new BookResponse();
        response.setBookDetails(bookDetailsService.saveBook(details));
        response.setSuccessMessage("Saved Book Details Successfully...");
        return response;
    }

    @PutMapping
    public String update(@RequestBody BookDetails details){
        bookDetailsService.updateBook(details);
        return "Updated Book Details Successfully...";
    }

    @PostMapping("/save-all")
    public String saveAll(@RequestBody List<BookDetails> detailsList){
        LOGGER.info("Total Request object to save : {}",detailsList.size());
        List<String> duplicateBookNames = new ArrayList<>();
        String message = "Saved All Book Details Successfully...";
        detailsList.stream().parallel().forEach(book -> {
            try {
                bookDetailsService.saveBook(book);
            }catch (Exception e){
                LOGGER.error("Error while saving Book details for bookName: {} and Error is: {}",
                        book.getBookName(), e.getMessage());
                duplicateBookNames.add(book.getBookName());
            }
        });
        if(Util.isHaveValue(duplicateBookNames)){
            if(duplicateBookNames.size() == detailsList.size()){
                message = "Book details all are available, Duplicate book details.";
            } else {
                message = String.format("Some Books are saved but, Duplicate Books are available names: %s", duplicateBookNames);
            }
            LOGGER.info(message);
        }
        return message;
    }

}
