package org.bookmanagement.Bo.Custom;

import org.bookmanagement.Bo.AdminRegisterService;
import org.bookmanagement.Dao.AdminRepository;
import org.bookmanagement.Dao.Custom.RepositoryFactory;
import org.bookmanagement.Dto.AdminDto;
import org.bookmanagement.Entity.Admin;
import org.bookmanagement.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AdminRegisterServiceImpl implements AdminRegisterService {
    private final AdminRepository adminRepository = (AdminRepository) RepositoryFactory.getDaoFactory().getDao(RepositoryFactory.DaoType.admin);

    private Session session;

    private Transaction transaction;

    @Override
    public int saveAdmin(AdminDto adminDto) {
        session = SessionFactoryConfiguration.getInstance().getSession();
        adminRepository.SetSession(session);
        int saved = adminRepository.saved(new Admin(adminDto.getId(), adminDto.getName(), adminDto.getUsername(), adminDto.getPassword(), adminDto.getEmail()));

        transaction = session.beginTransaction();
        transaction.commit();
        if (saved > 0) {
            return saved;
        } else {
            transaction.rollback();
            return -1;
        }
    }
}
