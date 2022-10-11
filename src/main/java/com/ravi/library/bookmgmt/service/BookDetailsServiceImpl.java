package com.ravi.library.bookmgmt.service;

import com.ravi.library.bookmgmt.entity.BookDetails;
import com.ravi.library.bookmgmt.exception.BookDetailsNotFoundException;
import com.ravi.library.bookmgmt.exception.BookMgmtCommonException;
import com.ravi.library.bookmgmt.repository.BookDetailsRepository;
import com.ravi.library.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookDetailsServiceImpl implements  BookDetailsService{

    private static final Logger LOGGER = LoggerFactory.getLogger(BookDetailsServiceImpl.class);

    @Autowired
    private BookDetailsRepository bookDetailsRepository;

    @Override
    public BookDetails findBook(Long id) throws BookMgmtCommonException {
        Optional<BookDetails> bookOptional = bookDetailsRepository.findById(id);
        return bookOptional.orElseThrow(BookDetailsNotFoundException::new);
    }

    @Override
    public BookDetails saveBook(BookDetails book) throws BookMgmtCommonException {
        List<BookDetails> duplicateBooks = bookDetailsRepository.findByBookNameIgnoreCase(book.getBookName());
        if(Util.isHaveValue(duplicateBooks)) {
            throw new BookMgmtCommonException("Duplicate record with book name: "+book.getBookName());
        }
        bookDetailsRepository.save(book);
        LOGGER.info("Book details are saved successfully...");
        return book;
    }

    @Override
    public BookDetails updateBook(BookDetails book) throws BookMgmtCommonException {
        if(book.getId() != null ) {
            BookDetails existingBookRec = findBook(book.getId());
            if(!existingBookRec.getBookName().equalsIgnoreCase(book.getBookName())){
                LOGGER.error("Book Details are not matching, existing book details are from DB: {}",existingBookRec);
                throw new BookMgmtCommonException("Book Details are not matching for updating the details");
            }
        }
        bookDetailsRepository.save(book);
        LOGGER.info("Book details are updated successfully...");
        return book;
    }

    @Override
    public void deleteBook(Long id) throws BookMgmtCommonException {
        bookDetailsRepository.deleteById(id);
        LOGGER.info("Book details are deleted successfully...");
    }

    @Override
    public List<BookDetails> findAllBooks() throws BookMgmtCommonException {
        List<BookDetails> bookList = bookDetailsRepository.findAll();
        if(Util.isNoValue(bookList)){
            throw  new BookDetailsNotFoundException("Books are not available..");
        }
        LOGGER.info("Total books size: {}",bookList.size());
        return bookList;
    }

    @Override
    public List<BookDetails> findBooksByAuthor(String author) throws BookMgmtCommonException {
        List<BookDetails> bookList = bookDetailsRepository.getBooksByAuthorNameContains(author.toLowerCase());

        if(Util.isNoValue(bookList)){
            throw  new BookDetailsNotFoundException("Books are not find for author: "+author);
        }
        LOGGER.info("Total books size: {} for author: {} ",bookList.size(),author);
        return bookList;
    }

    @Override
    public List<BookDetails> findBooksByBookName(String bookName) throws BookMgmtCommonException {
        List<BookDetails> bookList = bookDetailsRepository.findByBookNameContainingIgnoreCase(bookName);

        if(Util.isNoValue(bookList)){
            throw  new BookDetailsNotFoundException("Books are not find for name: "+bookName);
        }
        LOGGER.info("Total books size: {} for BookName: {} ",bookList.size(),bookName);
        return bookList;
    }

    @Override
    public List<BookDetails> findByCountryContainingIgnoreCase(String country) throws BookMgmtCommonException {
        List<BookDetails> bookList = bookDetailsRepository.findByCountryContainingIgnoreCase(country);

        if(Util.isNoValue(bookList)){
            throw  new BookDetailsNotFoundException("Books are not find for country: "+country);
        }
        LOGGER.info("Total books size: {} for Country: {} ",bookList.size(),country);
        return bookList;
    }
}
