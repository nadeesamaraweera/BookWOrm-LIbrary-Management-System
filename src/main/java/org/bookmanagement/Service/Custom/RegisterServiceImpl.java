package org.bookmanagement.Service.Custom;

import org.bookmanagement.Service.RegisterService;
import org.bookmanagement.Repository.Custom.RepositoryFactory;
import org.bookmanagement.Repository.UserRepository;
import org.bookmanagement.Dto.UserDto;
import org.bookmanagement.Entity.User;
import org.bookmanagement.configure.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RegisterServiceImpl implements RegisterService {

    private final UserRepository userRepository = (UserRepository) RepositoryFactory.getDaoFactory().getDao(RepositoryFactory.DaoType.User);
    private Session session;
    private Transaction transaction;
    @Override
    public int Register(UserDto member) {
        session = SessionFactoryConfiguration.getInstance().getSession();
        userRepository.SetSession(session);
        int saved = userRepository.saved(new User(member.getId(),member.getFull_name(),member.getUsername(),member.getPassword(),member.getEmail()));
        transaction = session.beginTransaction();
        if (saved > 0) {
            transaction.commit();
            session.close();
        }
        else {
            transaction.rollback();
            session.close();
        }
        return saved;
    }
}
