package org.bookmanagement.Repository.Custom;

import org.bookmanagement.Repository.UserRepository;
import org.bookmanagement.Entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private Session session;;

    @Override
    public User getData ( String Id ) {
        String hql = "FROM User WHERE username = :username";
        Query<User> query = session.createQuery(hql,User.class);
        query.setParameter("username", Id);
        return query.uniqueResult();
    }

//    public User getId(int id){
//        return  session.get(User.class,id);
//    }

    @Override
    public List<String> getOneData() {
        return null;
    }

    @Override
    public int saved(User data) {
        int value = (int) session.save(data);
        return value;
    }

    @Override
    public ArrayList<User> getAll() {
        String sqlQuery = "FROM User";
        Query query = session.createQuery(sqlQuery);
        List list = query.list();
        return (ArrayList<User>) list;
    }

    @Override
    public User getId(int id){
        return session.get(User.class, id);
    }

    @Override
    public void Update(User Data) {
        session.update(Data);
    }

    @Override
    public void Delete(int Id) {
        User user = session.get(User.class, Id);
        session.delete(user);
    }

    @Override
    public long Count() {
        String sql = "select count(*) from User ";
        org.hibernate.Query query = session.createQuery(sql);
        return (long) query.uniqueResult();
    }

    @Override
    public void SetSession(Session session) {
        this.session = session;
    }

    @Override
    public User CheckEmail(String email) {
        String hql = "FROM User WHERE email = :email";
        Query<User> query = session.createQuery(hql,User.class);
        query.setParameter("email", email);
        return query.uniqueResult();
    }
}

