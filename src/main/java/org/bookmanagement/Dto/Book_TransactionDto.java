package org.bookmanagement.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bookmanagement.Entity.Books;
import org.bookmanagement.Entity.BorrowBook;
import org.bookmanagement.Entity.TransactionDetailPK;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book_TransactionDto {
    private TransactionDetailPK id;
    private BorrowBook transaction;
    private Books book;
}
