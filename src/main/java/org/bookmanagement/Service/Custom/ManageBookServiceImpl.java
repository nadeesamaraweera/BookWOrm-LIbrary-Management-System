package org.bookmanagement.Service.Custom;

import org.bookmanagement.Service.ManageBookService;
import org.bookmanagement.Repository.BookRepository;
import org.bookmanagement.Repository.Custom.RepositoryFactory;
import org.bookmanagement.Dto.BookDto;
import org.bookmanagement.Entity.Book;
import org.bookmanagement.configure.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ManageBookServiceImpl implements ManageBookService {

    private final BookRepository bookRepository = (BookRepository) RepositoryFactory.getDaoFactory().getDao(RepositoryFactory.DaoType.Books);
    private Session session;
    private Transaction transaction;
    @Override
    public ArrayList<BookDto> getAll() {
        session = SessionFactoryConfiguration.getInstance().getSession();
        bookRepository.SetSession(session);
        ArrayList<Book> all = bookRepository.getAll();

        ArrayList<BookDto> books = new ArrayList<>();

        for (Book books1 : all){
            books.add(new BookDto(books1.getId(),books1.getTitle(),books1.getAutor(),books1.getDis(),books1.getGenre(),books1.getAvailable()));
        }
        session.close();
        return books;
    }

    @Override
    public void Update(BookDto Data) {
        session = SessionFactoryConfiguration.getInstance().getSession();
        bookRepository.SetSession(session);
        bookRepository.Update(new Book(Data.getId(),Data.getTitle(),Data.getAutor(),Data.getDis(),Data.getGenre(),Data.getAvailable(),AdminServiceImpl.admin));
        transaction = session.beginTransaction();
        transaction.commit();
        session.close();
    }

    @Override
    public void Delete(int Id) {
        session = SessionFactoryConfiguration.getInstance().getSession();
        bookRepository.SetSession(session);
        bookRepository.Delete(Id);
        transaction = session.beginTransaction();
        transaction.commit();
        session.close();
    }

    @Override
    public int Save(BookDto yes) {
        session = SessionFactoryConfiguration.getInstance().getSession();
        bookRepository.SetSession(session);
        int saved = bookRepository.saved(new Book(yes.getId(), yes.getTitle(), yes.getAutor(), yes.getDis(), yes.getGenre(), yes.getAvailable(),AdminServiceImpl.admin));
        transaction = session.beginTransaction();
        transaction.commit();
        if (saved > 0) {
            session.close();
            return saved;
        }
        else {
            transaction.rollback();
            session.close();
            return -1;
        }
    }

    @Override
    public BookDto search(String text) {
        session = SessionFactoryConfiguration.getInstance().getSession();
        bookRepository.SetSession(session);
        Book data = bookRepository.getData(text);
        session.close();
        return new BookDto(data.getId(), data.getTitle(), data.getAutor(), data.getDis(), data.getGenre(), data.getAvailable());
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
