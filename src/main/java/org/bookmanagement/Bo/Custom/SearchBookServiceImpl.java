package org.bookmanagement.Bo.Custom;


import org.bookmanagement.Bo.SearchBookService;
import org.bookmanagement.Dao.BookRepository;
import org.bookmanagement.Dao.Custom.RepositoryFactory;
import org.bookmanagement.Dto.BookDto;
import org.bookmanagement.Entity.Books;
import org.bookmanagement.util.SessionFactoryConfiguration;
import org.hibernate.Session;

import java.util.List;

public class SearchBookServiceImpl implements SearchBookService {

    private final BookRepository bookRepository = (BookRepository) RepositoryFactory.getDaoFactory().getDao(RepositoryFactory.DaoType.Books);
    private Session session;
    @Override
    public BookDto getData(String title) {
        session = SessionFactoryConfiguration.getInstance().getSession();
        bookRepository.SetSession(session);
        Books data = bookRepository.getData(title);

        return new BookDto( data.getId() , data.getTitle() , data.getAutor(), data.getDis(), data.getGenre() , data.getAvailable());
    }

    @Override
    public List<String> getTitles() {
        session = SessionFactoryConfiguration.getInstance().getSession();
        bookRepository.SetSession(session);
        return bookRepository.getOneData();
    }

}
