package org.bookmanagement.Service;

import org.bookmanagement.Dto.AdminDto;

public interface AdminRegisterService extends SuperService{
    int saveAdmin(AdminDto adminDto);
}
