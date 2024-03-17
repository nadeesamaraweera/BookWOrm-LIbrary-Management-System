package org.bookmanagement.configure;

import org.bookmanagement.Entity.*;
import org.bookmanagement.util.Utility;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.swing.text.Utilities;

public class SessionFactoryConfiguration {
    private static SessionFactoryConfiguration sessionFactoryConfiguration;
    private final SessionFactory sessionFactory;

    private SessionFactoryConfiguration(){
        sessionFactory = new Configuration()
                .mergeProperties(Utility.getProperties())
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Admin.class)
                .addAnnotatedClass(Book.class)
                .addAnnotatedClass(Branch.class)
                .addAnnotatedClass(BorrowBook.class)
                .addAnnotatedClass(Book_Transaction.class)
                .buildSessionFactory();
    }

    public static SessionFactoryConfiguration getInstance(){
        return (null == sessionFactoryConfiguration) ? sessionFactoryConfiguration = new SessionFactoryConfiguration()
                : sessionFactoryConfiguration;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }
}
