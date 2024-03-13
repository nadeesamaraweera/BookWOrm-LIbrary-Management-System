package org.bookmanagement.Bo.Custom;

import org.bookmanagement.Bo.DashboardService;
import org.bookmanagement.Dao.AdminRepository;
import org.bookmanagement.Dao.BookRepository;
import org.bookmanagement.Dao.BranchRepository;
import org.bookmanagement.Dao.Custom.RepositoryFactory;
import org.bookmanagement.Dto.AdminDto;
import org.bookmanagement.Entity.Admin;
import org.bookmanagement.configure.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DashboardServiceImpl implements DashboardService {

    private final AdminRepository adminRepository = (AdminRepository) RepositoryFactory.getDaoFactory().getDao( RepositoryFactory.DaoType.admin );
    private final BookRepository bookRepository = (BookRepository) RepositoryFactory.getDaoFactory().getDao( RepositoryFactory.DaoType.Books );

    private final BranchRepository branchRepository = (BranchRepository) RepositoryFactory.getDaoFactory().getDao( RepositoryFactory.DaoType.Branch );

    private Session session;
    private Transaction transaction;
    @Override
    public void Update(AdminDto Data) throws Exception {
        session = SessionFactoryConfiguration.getInstance().getSession();
        adminRepository.SetSession(session);
        Admin admin = new Admin();
        admin.setId(Data.getId());
        admin.setName(Data.getName());
        admin.setUsername(Data.getUsername());
        admin.setPassword(Data.getPassword());
        admin.setEmail(Data.getEmail());
        adminRepository.Update(admin);
        transaction = session.beginTransaction();
        transaction.commit();
    }

    @Override
    public void Delete(int id) {
        session = SessionFactoryConfiguration.getInstance().getSession();
        adminRepository.SetSession(session);
        adminRepository.Delete(id);
        transaction = session.beginTransaction();
        transaction.commit();
    }

    @Override
    public long BookCount(){
        session = SessionFactoryConfiguration.getInstance().getSession();
        bookRepository.SetSession(session);
        return bookRepository.Count();
    }
    @Override
    public long MemberCount(){
        session = SessionFactoryConfiguration.getInstance().getSession();
        adminRepository.SetSession(session);
        return adminRepository.Count();
    }
    @Override
    public long BranchCount(){
        session = SessionFactoryConfiguration.getInstance().getSession();
        branchRepository.SetSession(session);
        return branchRepository.Count();
    }
}
