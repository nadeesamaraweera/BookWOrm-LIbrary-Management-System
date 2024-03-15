package org.bookmanagement.Repository;

import org.bookmanagement.Entity.User;

public interface MemberRepository extends CrudUtil<User>{
    User CheckEmail(String email);

}
