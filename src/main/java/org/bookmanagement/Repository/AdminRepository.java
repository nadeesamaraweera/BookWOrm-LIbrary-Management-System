package org.bookmanagement.Repository;

import org.bookmanagement.Entity.Admin;

public interface AdminRepository extends CrudUtil<Admin>{
    Admin getData();

    Admin CheckEmail(String email);
}
