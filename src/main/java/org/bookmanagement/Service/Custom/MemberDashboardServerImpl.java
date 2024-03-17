package org.bookmanagement.Service.Custom;

import org.bookmanagement.Service.MemberDashboardServer;
import org.bookmanagement.Repository.BorrowBookRepository;
import org.bookmanagement.Repository.Custom.RepositoryFactory;
import org.bookmanagement.Repository.UserRepository;
import org.bookmanagement.Dto.UserDto;
import org.bookmanagement.Entity.User;
import org.bookmanagement.configure.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MemberDashboardServerImpl implements MemberDashboardServer {
    private final UserRepository userRepository = (UserRepository) RepositoryFactory.getDaoFactory().getDao(RepositoryFactory.DaoType.User);

    private final BorrowBookRepository borrowBook = (BorrowBookRepository) RepositoryFactory.getDaoFactory().getDao(RepositoryFactory.DaoType.BorrowBook);
    private Session session;
    private Transaction transaction;
    @Override
    public UserDto getData(String username) {
        session = SessionFactoryConfiguration.getInstance().getSession();
        userRepository.SetSession(session);
        User data = userRepository.getData(username);
        session.close();
        return new UserDto(data.getId(), data.getFull_name(), data.getUsername(), data.getPassword(), data.getEmail());
    }

    @Override
    public void Update(UserDto memberDto) {
        session = SessionFactoryConfiguration.getInstance().getSession();
        userRepository.SetSession(session);
        userRepository.Update(new User(memberDto.getId(), memberDto.getFull_name(), memberDto.getUsername(), memberDto.getPassword(), memberDto.getEmail()));
        transaction = session.beginTransaction();
        transaction.commit();
        session.close();
    }

    @Override
    public int BookCount(String memberUsername) {
        session = SessionFactoryConfiguration.getInstance().getSession();
        userRepository.SetSession(session);
        User data = userRepository.getData(memberUsername);
        borrowBook.SetSession(session);
        int bookCount = borrowBook.BookCount(data);
        session.close();
        return bookCount;
    }
}
