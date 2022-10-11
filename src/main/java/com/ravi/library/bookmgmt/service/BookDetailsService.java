package com.ravi.library.bookmgmt.service;

import com.ravi.library.bookmgmt.entity.BookDetails;
import com.ravi.library.bookmgmt.exception.BookMgmtCommonException;

import java.util.List;

public interface BookDetailsService {

    public BookDetails findBook(Long id) throws BookMgmtCommonException;

    public BookDetails saveBook(BookDetails book) throws BookMgmtCommonException;

    public BookDetails updateBook(BookDetails book) throws BookMgmtCommonException;

    public void deleteBook(Long id) throws BookMgmtCommonException;

    public List<BookDetails> findAllBooks() throws BookMgmtCommonException;

    public List<BookDetails> findBooksByAuthor(String author) throws BookMgmtCommonException;

    public List<BookDetails> findBooksByBookName(String bookName) throws BookMgmtCommonException;

    public List<BookDetails> findByCountryContainingIgnoreCase(String country) throws BookMgmtCommonException;
}
