package com.ravi.library.bookmgmt.repository;

import com.ravi.library.bookmgmt.entity.BookDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDetailsRepository extends JpaRepository<BookDetails, Long> {

    /*
      Full Author Name match and ignore cases
     */
    public List<BookDetails> findByAuthorIgnoreCase(String authorName);

    /**
     * Partial Author name match like and ignore cases
     */
    @Query(nativeQuery = true, value = "select * from BOOK_DETAILS where LOWER(author) like %:author%")
    public List<BookDetails> getBooksByAuthorNameContains(@Param("author") String authorName);


    /**
     * Partial Book name match like and ignore cases
     */
    public List<BookDetails> findByBookNameContainingIgnoreCase(String bookName);

    /*
     Full Book Name match and ignore cases
    */
    public List<BookDetails> findByBookNameIgnoreCase(String bookName);

    /**
     * Partial Country name match like and ignore cases
     */
    public List<BookDetails> findByCountryContainingIgnoreCase(String country);
}