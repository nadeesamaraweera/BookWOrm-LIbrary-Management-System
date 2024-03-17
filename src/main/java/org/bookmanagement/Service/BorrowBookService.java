package org.bookmanagement.Service;

import org.bookmanagement.Dto.BookDto;

import java.util.List;

public interface BorrowBookService extends SuperService{

    List<String> getTitles();

    BookDto getData(String title);
    boolean saveTransaction(List<String> data);
    boolean getPendingBook(String id);
}
