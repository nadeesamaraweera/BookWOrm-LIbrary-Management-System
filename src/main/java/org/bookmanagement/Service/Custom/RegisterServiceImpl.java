package org.bookmanagement.Service.Custom;

import org.bookmanagement.Service.RegisterService;
import org.bookmanagement.Repository.Custom.RepositoryFactory;
import org.bookmanagement.Repository.MemberRepository;
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
