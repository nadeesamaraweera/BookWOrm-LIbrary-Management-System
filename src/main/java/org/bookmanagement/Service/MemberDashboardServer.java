package org.bookmanagement.Service;

import org.bookmanagement.Dto.UserDto;

public interface MemberDashboardServer extends SuperService{
    UserDto getData(String username);

    void Update(UserDto memberDto);

    int BookCount(String memberUsername);
}
