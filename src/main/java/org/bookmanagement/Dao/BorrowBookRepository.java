package org.bookmanagement.Dao;

import org.bookmanagement.Entity.BorrowBook;
import org.bookmanagement.Entity.User;

public interface BorrowBookRepository extends CrudUtil<BorrowBook>{

    BorrowBook getData(User Id);


    int BookCount(User data);

    BorrowBook getPendingBook(User id) ;
}
