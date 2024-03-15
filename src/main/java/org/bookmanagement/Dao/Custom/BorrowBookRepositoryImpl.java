package org.bookmanagement.Dao.Custom;

import org.bookmanagement.Bo.Custom.MemberServiceImpl;
import org.bookmanagement.Dao.BorrowBookRepository;
import org.bookmanagement.Entity.BorrowBook;
import org.bookmanagement.Entity.User;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class BorrowBookRepositoryImpl implements BorrowBookRepository {
    private Session session;
    @Override
    public BorrowBook getData(String Id) {
        Query query = session.createQuery("from BorrowBook where id = :id");
        query.setParameter("id", 9);
        return (BorrowBook) query.uniqueResult();
    }

    @Override
    public List<String> getOneData() {
        return null;
    }

    @Override
    public int saved(BorrowBook data) {
        return (int) session.save(data);
    }

    @Override
    public ArrayList<BorrowBook> getAll() {
        String sql = "SELECT B FROM BorrowBook As B where B.user = :User";
        Query query = session.createQuery(sql);
        query.setParameter("User", MemberServiceImpl.member);
        return (ArrayList<BorrowBook>) query.getResultList();
    }

    @Override
    public void Update(BorrowBook Data) {
        session.update(Data);
    }

    @Override
    public void Delete(int Id) {

    }

    @Override
    public long Count() {
        String sql = "SELECT SUM(B.payment) FROM BorrowBook AS B";
        Query query = session.createQuery(sql);
        Double sum = (Double) query.getSingleResult();
        return sum != null ? sum.longValue() : 0; // Return 0 if sum is null
    }

    @Override
    public void SetSession(Session session) {
        this.session = session;
    }

    @Override
    public BorrowBook getData(User Id) {
        String sql = "SELECT B FROM BorrowBook As B where B.user = :id and B.status = :status";
        Query query = session.createQuery(sql);
        query.setParameter("id", Id);
        query.setParameter("status", "Pending");
        return (BorrowBook) query.uniqueResult();
    }

    @Override
    public int BookCount(User data) {
        String sql = "SELECT COUNT(B) FROM BorrowBook As B where B.user = :User";
        Query query = session.createQuery(sql);
        query.setParameter("User", data);
        return ((Long) query.uniqueResult()).intValue();
    }
    @Override
    public BorrowBook getPendingBook(User id){
        String sql = "SELECT B FROM BorrowBook As B where B.user = :User and B.status = :status";
        Query query = session.createQuery(sql);
        query.setParameter("User", id);
        query.setParameter("status", "Pending");
        return (BorrowBook) query.uniqueResult();
    }
}
