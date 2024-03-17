package org.bookmanagement.Service;

import org.bookmanagement.Dto.UserDto;

public interface RegisterService extends SuperService {
    int Register(UserDto member);
}
