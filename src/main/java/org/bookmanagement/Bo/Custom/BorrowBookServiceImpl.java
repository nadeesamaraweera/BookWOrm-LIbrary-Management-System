package org.bookmanagement.Bo.Custom;

import org.bookmanagement.Bo.BorrowBookService;
import org.bookmanagement.Dao.BookRepository;
import org.bookmanagement.Dao.BookTransactionRepository;
import org.bookmanagement.Dao.BorrowBookRepository;
import org.bookmanagement.Dao.Custom.RepositoryFactory;
import org.bookmanagement.Dao.MemberRepository;
import org.bookmanagement.Dto.BookDto;
import org.bookmanagement.Entity.Book_Transaction;
import org.bookmanagement.Entity.Books;
import org.bookmanagement.Entity.BorrowBook;
import org.bookmanagement.Entity.User;
import org.bookmanagement.util.GetIdNumber;
import org.bookmanagement.configure.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BorrowBookServiceImpl implements BorrowBookService {

    private Session session;

    private final BookRepository bookRepository = (BookRepository) RepositoryFactory.getDaoFactory().getDao(RepositoryFactory.DaoType.Books);
    private final BorrowBookRepository borrowBookRepository = (BorrowBookRepository) RepositoryFactory.getDaoFactory().getDao(RepositoryFactory.DaoType.BorrowBook);
    private final BookTransactionRepository transactionRepository = (BookTransactionRepository) RepositoryFactory.getDaoFactory().getDao(RepositoryFactory.DaoType.Book_Transaction);
    private final MemberRepository memberRepository = (MemberRepository) RepositoryFactory.getDaoFactory().getDao(RepositoryFactory.DaoType.Member);

    @Override
    public List<String> getTitles() {
        session = SessionFactoryConfiguration.getInstance().getSession();
        bookRepository.SetSession(session);
        return bookRepository.getOneData();
    }

    @Override
    public BookDto getData(String title) {
        session = SessionFactoryConfiguration.getInstance().getSession();
        bookRepository.SetSession(session);
        Books data = bookRepository.getData(title);

        return new BookDto(data.getId(), data.getTitle(), data.getAutor(), data.getDis(), data.getGenre(), data.getAvailable());
    }

    @Override
    public boolean saveTransaction(List<String> data) {

        User member = MemberServiceImpl.member;

        List<Books> books = new ArrayList<>();

        session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        for (String title : data) {
            bookRepository.SetSession(session);
            books.add(bookRepository.getData(title));
        }

        Date date = Date.valueOf(LocalDate.now().plusDays(7));


        try {
            BorrowBook borrowBook = new BorrowBook(
                    -1,
                    data.size(),
                    date,
                    "Pending",
                    null,
                    member,
                    0,
                    new ArrayList<>()
            );

            borrowBookRepository.SetSession(session);
            int saved = borrowBookRepository.saved(borrowBook);

            for (Books book : books) {
                book.setAvailable("No");
                bookRepository.SetSession(session);
                bookRepository.Update(book);

                Book_Transaction bookTransaction = new Book_Transaction(
                        -1,
                        borrowBook,
                        book
                );

                transactionRepository.SetSession(session);
                transactionRepository.saved(bookTransaction);
            }

            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;

        } finally {
            session.close();
        }
    }

    @Override
    public boolean getPendingBook(String id){
        int memberId = GetIdNumber.getIdNumber("M", id);
        session = SessionFactoryConfiguration.getInstance().getSession();

        memberRepository.SetSession(session);
        User member = memberRepository.getData(""+memberId);

        borrowBookRepository.SetSession(session);
        BorrowBook data = borrowBookRepository.getPendingBook(member);
        session.close();

        if(data == null){
            return true;
        }
        else {
            return false;
        }
    }
}
