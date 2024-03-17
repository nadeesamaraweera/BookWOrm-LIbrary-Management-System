package org.bookmanagement.Service.Custom;

import org.bookmanagement.Service.UserService;
import org.bookmanagement.Repository.Custom.RepositoryFactory;
import org.bookmanagement.Repository.UserRepository;
import org.bookmanagement.Entity.User;
import org.bookmanagement.configure.SessionFactoryConfiguration;
import org.hibernate.Session;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository = (UserRepository) RepositoryFactory.getDaoFactory().getDao( RepositoryFactory.DaoType.User );

    private Session session;

    public static User user;
    @Override
    public boolean Login(String Username, String Password) {
        session = SessionFactoryConfiguration.getInstance().getSession();
        userRepository.SetSession(session);
        User data = userRepository.getData(Username);

        if (data != null && data.getPassword().equals(Password)){
            user = data;
            session.close();
            return true;
        }
        else {
            session.close();
            return false;
        }
    }
}
