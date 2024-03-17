package org.bookmanagement.Repository;

import org.bookmanagement.Dto.PendingDto;
import org.hibernate.Session;

import java.util.List;

public interface CustomRepository extends SuperRepository {

    List<PendingDto> getPendingDtoList();

    public void SetSession(Session session);
}
