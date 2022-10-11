package com.ravi.library.bookmgmt.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ravi.library.bookmgmt.entity.BookDetails;
import com.ravi.library.util.Util;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookResponse {

    private Integer recordsCount;
    private String successMessage;
    private String errorMessage;
    private BookDetails bookDetails;
    private List<BookDetails> bookList;

    public Integer getRecordsCount() {
        if(Util.isHaveValue(bookList)){
            recordsCount = bookList.size();
        } else if(bookDetails != null){
            recordsCount = 1;
        }
        return recordsCount;
    }
}
