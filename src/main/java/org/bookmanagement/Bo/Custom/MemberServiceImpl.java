package org.bookmanagement.Bo.Custom;

import org.bookmanagement.Bo.MemberService;
import org.bookmanagement.Dao.Custom.RepositoryFactory;
import org.bookmanagement.Dao.MemberRepository;
import org.bookmanagement.Entity.Member;
import org.bookmanagement.configure.SessionFactoryConfiguration;
import org.hibernate.Session;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository = (MemberRepository) RepositoryFactory.getDaoFactory().getDao( RepositoryFactory.DaoType.Member );

    private Session session;

    public static Member member;
    @Override
    public boolean Login(String Username, String Password) {
        session = SessionFactoryConfiguration.getInstance().getSession();
        memberRepository.SetSession(session);
        Member data = memberRepository.getData(Username);

        if (data != null && data.getPassword().equals(Password)){
            member = data;
            return true;
        }
        else {
            return false;
        }
    }
}
