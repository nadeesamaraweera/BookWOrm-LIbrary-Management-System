package org.bookmanagement.Dao;

import org.bookmanagement.Entity.Book;


public interface BookRepository extends CrudUtil<Book>{
    Book getData(String title);
}
