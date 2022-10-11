package com.ravi.library.service;

import com.ravi.library.bookmgmt.entity.BookDetails;
import com.ravi.library.bookmgmt.repository.BookDetailsRepository;
import com.ravi.library.bookmgmt.service.BookDetailsService;
import com.ravi.library.bookmgmt.service.BookDetailsServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookDetailsServiceImplTest {

    @TestConfiguration
    static class BookDetailsServiceImplTestContextConfiguration{
        @Bean
        public BookDetailsService bookDetailsService(){
            return new BookDetailsServiceImpl();
        }
    }

    @Autowired
    private BookDetailsService bookDetailsService;

    @MockBean
    private BookDetailsRepository bookDetailsRepository;

    BookDetails bookDetails = new BookDetails();
    List<BookDetails> bookDetailsList = new ArrayList<>();

    @Before
    public  void setup(){
        bookDetails = new BookDetails();
        bookDetails.setBookName("Things Fall Apart");
        bookDetails.setAuthor("Chinua Achebe");
        bookDetails.setBookType("");
        bookDetails.setCountry("Nigeria");
        bookDetails.setLanguage("English");
        bookDetails.setImageLink("images/things-fall-apart.jpg");
        bookDetails.setPages(209L);
        bookDetails.setLink("https://en.wikipedia.org/wiki/Things_Fall_Apart\\n");
        bookDetails.setYear(1958L);

        bookDetailsList.add(bookDetails);

    }

    @Test
    public void findBooksByBookName_Test(){
        String bookName = "Things Fall Apart";

        Mockito.when(bookDetailsRepository.findByBookNameContainingIgnoreCase(bookName))
                .thenReturn(bookDetailsList);

        List<BookDetails> foundBookDetails = bookDetailsService.findBooksByBookName(bookName);

        Assertions.assertThat(foundBookDetails.get(0).getBookName())
                .isEqualToIgnoringCase(bookName);

    }

    @Test
    public void findBooksByAuthor_Test(){
        String authorName = "chinua";

        Mockito.when(bookDetailsRepository.getBooksByAuthorNameContains(authorName))
                .thenReturn(bookDetailsList);

        List<BookDetails> foundBookDetails = bookDetailsService.findBooksByAuthor(authorName);

        Assertions.assertThat(foundBookDetails.get(0).getAuthor())
                .containsIgnoringCase(authorName);

    }

    @Test
    public void findAllBooks_Test(){ ;

        Mockito.when(bookDetailsRepository.findAll()).thenReturn(bookDetailsList);

        List<BookDetails> foundBookDetails = bookDetailsService.findAllBooks();

        Assertions.assertThat(foundBookDetails.size()).isEqualTo(bookDetailsList.size());

    }



}
