package org.bookmanagement.Service.Custom;

import org.bookmanagement.Service.UserMangeService;
import org.bookmanagement.Repository.Custom.RepositoryFactory;
import org.bookmanagement.Repository.UserRepository;
import org.bookmanagement.Dto.UserDto;
import org.bookmanagement.Entity.User;
import org.bookmanagement.configure.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class UserManageServiceImpl implements UserMangeService {

    private final UserRepository userRepository = (UserRepository) RepositoryFactory.getDaoFactory().getDao(RepositoryFactory.DaoType.User);
    private Session session = SessionFactoryConfiguration.getInstance().getSession();

    private Transaction transaction;
    @Override
    public ArrayList<UserDto> getAll() {
        session = SessionFactoryConfiguration.getInstance().getSession();
        userRepository.SetSession(session);

        ArrayList<User> all = userRepository.getAll();

        ArrayList<UserDto> admins = new ArrayList<>();

        for (User admin : all) {
            admins.add(new UserDto(admin.getId(), admin.getFull_name(), admin.getUsername(), admin.getPassword(), admin.getEmail()));
        }

        session.close();
        return admins;
    }

    @Override
    public void delete(int Id) {
        session = SessionFactoryConfiguration.getInstance().getSession();
        userRepository.SetSession(session);
        userRepository.Delete(Id);
        transaction = session.beginTransaction();
        transaction.commit();
        session.close();
    }



}
