package org.bookmanagement.Dao;

import org.bookmanagement.Entity.BorrowBook;
import org.bookmanagement.Entity.Member;

public interface BorrowBookRepository extends CrudUtil<BorrowBook>{

    BorrowBook getData(Member Id);
}
