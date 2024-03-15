package org.bookmanagement.Repository;

import org.bookmanagement.Entity.Book_Transaction;

import java.util.List;

public interface BookTransactionRepository extends CrudUtil<Book_Transaction>{
    List<Book_Transaction> bookTransactionData(String id);

}
