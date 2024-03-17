package org.bookmanagement.Repository;

import org.bookmanagement.Entity.User;

public interface UserRepository extends CrudUtil<User>{
    User CheckEmail(String email);

    User getId(int id);

}
