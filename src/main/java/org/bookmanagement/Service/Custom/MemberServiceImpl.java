package org.bookmanagement.Service.Custom;

import org.bookmanagement.Service.MemberService;
import org.bookmanagement.Repository.Custom.RepositoryFactory;
import org.bookmanagement.Repository.MemberRepository;
import org.bookmanagement.Entity.User;
import org.bookmanagement.configure.SessionFactoryConfiguration;
import org.hibernate.Session;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository = (MemberRepository) RepositoryFactory.getDaoFactory().getDao( RepositoryFactory.DaoType.Member );

    private Session session;

    public static User member;
    @Override
    public boolean Login(String Username, String Password) {
        session = SessionFactoryConfiguration.getInstance().getSession();
        memberRepository.SetSession(session);
        User data = memberRepository.getData(Username);

        if (data != null && data.getPassword().equals(Password)){
            member = data;
            return true;
        }
        else {
            return false;
        }
    }
}
