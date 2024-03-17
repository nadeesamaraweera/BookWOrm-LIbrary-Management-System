package org.bookmanagement.Service.Custom;

import org.bookmanagement.Service.AdminService;
import org.bookmanagement.Repository.AdminRepository;
import org.bookmanagement.Repository.Custom.RepositoryFactory;
import org.bookmanagement.Dto.AdminDto;
import org.bookmanagement.Entity.Admin;
import org.bookmanagement.configure.SessionFactoryConfiguration;
import org.hibernate.Session;

public class AdminServiceImpl implements AdminService {

    private Session session;
    AdminRepository adminRepository = (AdminRepository) RepositoryFactory.getDaoFactory().getDao( RepositoryFactory.DaoType.admin );

    public static AdminDto data;

    public static Admin admin;
    @Override
    public boolean getData(String Id, String Password) {
        session = SessionFactoryConfiguration.getInstance().getSession();
        adminRepository.SetSession(session);
        Admin data = adminRepository.getData(Id);

        if (data != null && Password.equals(data.getPassword())){
            this.data = new AdminDto(data.getId(),data.getName(),data.getUsername(),data.getPassword(),data.getEmail());
            admin = data;
            session.close();
            return true;
        }
        else {
            session.close();
            return false;
        }
    }
}
