package org.bookmanagement.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bookmanagement.Entity.Book;

import org.bookmanagement.Entity.BorrowBook;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book_TransactionDto {
    private int id;
    private BorrowBook transaction;
    private Book book;
}
