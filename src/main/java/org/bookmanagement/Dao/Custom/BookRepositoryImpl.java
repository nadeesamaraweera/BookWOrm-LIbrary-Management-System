package org.bookmanagement.Dao.Custom;

import org.bookmanagement.Dao.BookRepository;
import org.bookmanagement.Entity.Book;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {

    private Session session;
    @Override
    public Book getData(String title) {
        String sql = "SELECT B FROM Book AS B WHERE B.title =: title";
        Query query = session.createQuery(sql);
        query.setParameter("title",title);
        return (Book) query.getSingleResult();
    }


    @Override
    public List<String> getOneData() {
        String sql = "SELECT B.title FROM Book AS B";
        Query query = session.createQuery(sql);
        List<String> titles = query.getResultList();
        return titles;
    }

    @Override
    public int saved(Book data) {
        return  (int) session.save(data);
    }

    @Override
    public ArrayList<Book> getAll() {
        String sql = "SELECT B FROM Book AS B";
        Query query = session.createQuery(sql);
        List list = query.list();
        return (ArrayList<Book>) list;
    }

    @Override
    public void Update(Book Data) {
        session.update(Data);
    }

    @Override
    public void Delete(int Id) {
        Book books = session.get(Book.class, Id);
        session.delete(books);
    }

    @Override
    public long Count() {
        String sql = "select count(*) from Book";
        Query query = session.createQuery(sql);
        return (long) query.uniqueResult();
    }

    @Override
    public void SetSession(Session session) {
        this.session = session;
    }

}
