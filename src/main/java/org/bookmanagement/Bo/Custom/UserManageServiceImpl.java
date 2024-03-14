package org.bookmanagement.Bo.Custom;

import org.bookmanagement.Bo.UserMangeService;
import org.bookmanagement.Dao.Custom.RepositoryFactory;
import org.bookmanagement.Dao.MemberRepository;
import org.bookmanagement.Dto.MemberDto;
import org.bookmanagement.Entity.User;
import org.bookmanagement.configure.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class UserManageServiceImpl implements UserMangeService {

    private final MemberRepository memberRepository = (MemberRepository) RepositoryFactory.getDaoFactory().getDao(RepositoryFactory.DaoType.Member);
    private Session session = SessionFactoryConfiguration.getInstance().getSession();

    private Transaction transaction;
    @Override
    public ArrayList<MemberDto> getAll() {
        session = SessionFactoryConfiguration.getInstance().getSession();
        memberRepository.SetSession(session);

        ArrayList<User> all = memberRepository.getAll();

        ArrayList<MemberDto> admins = new ArrayList<>();

        for (User admin : all) {
            admins.add(new MemberDto(admin.getId(), admin.getFull_name(), admin.getUsername(), admin.getPassword(), admin.getEmail()));
        }

        return admins;
    }

    @Override
    public void delete(int Id) {
        session = SessionFactoryConfiguration.getInstance().getSession();
        memberRepository.SetSession(session);
        memberRepository.Delete(Id);
        transaction = session.beginTransaction();
        transaction.commit();
    }



}
