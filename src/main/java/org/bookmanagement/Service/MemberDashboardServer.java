package org.bookmanagement.Service;

import org.bookmanagement.Dto.MemberDto;

public interface MemberDashboardServer extends SuperService{
    MemberDto getData(String username);

    void Update(MemberDto memberDto);

    int BookCount(String memberUsername);
}
