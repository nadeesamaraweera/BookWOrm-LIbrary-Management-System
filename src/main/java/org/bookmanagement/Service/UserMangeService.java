package org.bookmanagement.Service;

import org.bookmanagement.Dto.UserDto;

import java.util.ArrayList;

public interface UserMangeService extends SuperService {
    ArrayList<UserDto> getAll();

    void delete(int Id);

}
