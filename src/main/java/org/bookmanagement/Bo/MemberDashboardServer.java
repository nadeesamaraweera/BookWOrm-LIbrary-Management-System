package org.bookmanagement.Bo;

import org.bookmanagement.Dto.MemberDto;

public interface MemberDashboardServer extends SuperService{
    MemberDto getData(String username);

    void Update(MemberDto memberDto);
}