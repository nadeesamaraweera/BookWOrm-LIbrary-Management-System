package org.bookmanagement.Service.Custom;

import org.bookmanagement.Dto.PendingDto;
import org.bookmanagement.Repository.Custom.RepositoryFactory;
import org.bookmanagement.Repository.CustomRepository;
import org.bookmanagement.Service.PendingBookService;
import org.bookmanagement.configure.SessionFactoryConfiguration;
import org.hibernate.Session;

import java.util.List;

public class PendingBookServiceImpl implements PendingBookService {
    private Session session;
    private final CustomRepository customRepository = (CustomRepository) RepositoryFactory.getDaoFactory().getDao(RepositoryFactory.DaoType.Pending);

    @Override
    public List<PendingDto> getPendingBookDetails() {
        session = SessionFactoryConfiguration.getInstance().getSession();
        customRepository.SetSession(session);
        List<PendingDto> borrowBookDetails = customRepository.getPendingDtoList();
        session.close();
        System.out.println(2);
        return borrowBookDetails;
    }
}
