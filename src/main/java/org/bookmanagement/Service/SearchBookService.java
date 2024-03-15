package org.bookmanagement.Service;

import org.bookmanagement.Dto.BookDto;

import java.util.List;

public interface SearchBookService extends SuperService {
    BookDto getData(String title);
    List<String> getTitles();
}
