package org.bookmanagement.Dao;

import org.bookmanagement.Entity.Books;

public interface BookRepository extends CrudUtil<Books>{
    Books getData(String title);
}
