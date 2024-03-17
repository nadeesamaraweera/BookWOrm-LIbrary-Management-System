package org.bookmanagement.Service.Custom;


import org.bookmanagement.Service.SearchBookService;
import org.bookmanagement.Repository.BookRepository;
import org.bookmanagement.Repository.Custom.RepositoryFactory;
import org.bookmanagement.Dto.BookDto;
import org.bookmanagement.Entity.Book;
import org.bookmanagement.configure.SessionFactoryConfiguration;
import org.hibernate.Session;

import java.util.List;

public class SearchBookServiceImpl implements SearchBookService {

    private final BookRepository bookRepository = (BookRepository) RepositoryFactory.getDaoFactory().getDao(RepositoryFactory.DaoType.Books);
    private Session session;
    @Override
    public BookDto getData(String title) {
        session = SessionFactoryConfiguration.getInstance().getSession();
        bookRepository.SetSession(session);
        Book data = bookRepository.getData(title);
        session.close();

        return new BookDto( data.getId() , data.getTitle() , data.getAutor(), data.getDis(), data.getGenre() , data.getAvailable());
    }

    @Override
    public List<String> getTitles() {
        session = SessionFactoryConfiguration.getInstance().getSession();
        bookRepository.SetSession(session);
        List<String> list = bookRepository.getOneData();
        session.close();
        return list;
    }

}
