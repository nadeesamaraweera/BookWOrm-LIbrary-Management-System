package org.bookmanagement.Service;

import org.bookmanagement.Dto.AdminDto;

public interface DashboardService extends SuperService {
    void Update(AdminDto Data) throws Exception;

    void Delete(int id);

    long BookCount();

    long MemberCount();

    long BranchCount();
}
