package org.bookmanagement.Repository;

import org.hibernate.Session;

public interface SuperRepository {
    void SetSession(Session session);
}
