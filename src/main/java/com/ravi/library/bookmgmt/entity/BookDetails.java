package com.ravi.library.bookmgmt.entity;

import com.ravi.library.util.BaseEntity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "BOOK_DETAILS")
public class BookDetails extends BaseEntity {

    private String bookName;
    private String author;
    private String sellerName;
    private Long year;
    private String bookType;
    private String country;
    private String imageLink;
    private String language;
    private String link;
    private Long pages;


}
