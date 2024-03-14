package org.bookmanagement.Bo.Custom;

import org.bookmanagement.Bo.RegisterService;
import org.bookmanagement.Dao.Custom.RepositoryFactory;
import org.bookmanagement.Dao.MemberRepository;
import org.bookmanagement.Dto.MemberDto;
import org.bookmanagement.Entity.User;
import org.bookmanagement.configure.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RegisterServiceImpl implements RegisterService {

    private final MemberRepository memberRepository = (MemberRepository) RepositoryFactory.getDaoFactory().getDao(RepositoryFactory.DaoType.Member);
    private Session session;
    private Transaction transaction;
    @Override
    public int Register(MemberDto member) {
        session = SessionFactoryConfiguration.getInstance().getSession();
        memberRepository.SetSession(session);
        int saved = memberRepository.saved(new User(member.getId(),member.getFull_name(),member.getUsername(),member.getPassword(),member.getEmail()));
        transaction = session.beginTransaction();
        if (saved > 0) {
            transaction.commit();
        }
        else {
            transaction.rollback();
        }
        return saved;
    }
}
