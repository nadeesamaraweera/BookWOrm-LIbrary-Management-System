package org.bookmanagement.Bo.Custom;

import org.bookmanagement.Bo.ReturnBookServiceI;
import org.bookmanagement.Dao.BookRepository;
import org.bookmanagement.Dao.BookTransactionRepository;
import org.bookmanagement.Dao.BorrowBookRepository;
import org.bookmanagement.Dao.Custom.RepositoryFactory;
import org.bookmanagement.Dao.MemberRepository;
import org.bookmanagement.Entity.*;
import org.bookmanagement.util.GetIdNumber;
import org.bookmanagement.configure.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ReturnBookServiceImpl implements ReturnBookServiceI {

    private final MemberRepository memberRepository = (MemberRepository) RepositoryFactory.getDaoFactory().getDao(RepositoryFactory.DaoType.Member);

    private final BookTransactionRepository bookTransactionRepository = (BookTransactionRepository) RepositoryFactory.getDaoFactory().getDao(RepositoryFactory.DaoType.Book_Transaction);

    private final BorrowBookRepository borrowBookRepository = (BorrowBookRepository) RepositoryFactory.getDaoFactory().getDao(RepositoryFactory.DaoType.BorrowBook);

    private final BookRepository bookRepository = (BookRepository) RepositoryFactory.getDaoFactory().getDao(RepositoryFactory.DaoType.Books);
    private Session session;
    @Override
    public ArrayList<Integer> getAllId(){
        session = SessionFactoryConfiguration.getInstance().getSession();
        memberRepository.SetSession(session);
        ArrayList<User> all = memberRepository.getAll();
        ArrayList<Integer> ids = new ArrayList<>();
        for (User user : all) {
            ids.add(user.getId());
        }
        session.close();
        return ids;
    }

//    @Override
//    public List<Book_Transaction> getPendingData(String value) {
////        int MemberId = GetIdNumber.getIdNumber("M", value);
//       session = SessionFactoryConfiguration.getInstance().getSession();
////
////        borrowBookRepository.SetSession(session);
////        BorrowBook data = borrowBookRepository.getData("" + MemberId);
////
////        int BorrowId = data.getId();
////
////        session = SessionFactoryConfiguration.getInstance().getSession();
////        bookTransactionRepository.SetSession(session);
////        List<Book_Transaction> transactionList = bookTransactionRepository.bookTransactionData("" + BorrowId);
////
////        return transactionList;
//        borrowBookRepository.SetSession(session);
//        BorrowBook borrowBook = borrowBookRepository.getData("a");
//        List<Book_Transaction> details = borrowBook.getDetails();
//
//        for(Book_Transaction bookTransaction : details){
//            int id = bookTransaction.getBook().getId();
//            System.out.println(id);
//        }
//        return null;
//    }


    @Override
    public BorrowBook getPendingData(String value) {

        session = SessionFactoryConfiguration.getInstance().getSession();
        int u = GetIdNumber.getIdNumber("U", value);
        memberRepository.SetSession(session);
        User data1 = memberRepository.getData(""+u);

        session.close();

        session = SessionFactoryConfiguration.getInstance().getSession();
        borrowBookRepository.SetSession(session);
        BorrowBook data = borrowBookRepository.getData(data1);
        session.close();

//        List<Book_Transaction> details = data.getDetails();

//        for(Book_Transaction bookTransaction : details){
//            int id = bookTransaction.getBook().getId();
//
//            Books books = bookTransaction.getBook();
//            books.setAvailable("Yes");
//
//            session = SessionFactoryConfiguration.getInstance().getSession();
//            bookRepository.SetSession(session);
//            bookRepository.Update(books);
//            session.close();
//        }

        return data;
    }

    @Override
    public boolean returnBook(BorrowBook borrowBook) {

        session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            List<Book_Transaction> details = borrowBook.getDetails();

            for(Book_Transaction bookTransaction : details){
                int id = bookTransaction.getBook().getId();

                Book books = bookTransaction.getBook();
                books.setAvailable("Yes");
                System.out.println(books.getId());
                bookRepository.SetSession(session);
                bookRepository.Update(books);
            }

            borrowBookRepository.SetSession(session);
            borrowBook.setStatus("Yes");
            borrowBookRepository.Update(borrowBook);

            transaction.commit();

            session.close();
            return true;

        } catch (Exception e) {

            e.printStackTrace();
            transaction.rollback();
            session.close();
            return false;
        }
    }

    @Override
    public List<BorrowBook> getAllTableData() {
        session = SessionFactoryConfiguration.getInstance().getSession();
        borrowBookRepository.SetSession(session);
        List<BorrowBook> all = borrowBookRepository.getAll();

        session.close();

        return all;
    }

}
