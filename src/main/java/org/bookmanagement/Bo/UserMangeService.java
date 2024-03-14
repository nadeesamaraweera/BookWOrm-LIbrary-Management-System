package org.bookmanagement.Bo;

import org.bookmanagement.Dto.BranchDto;
import org.bookmanagement.Dto.MemberDto;

import java.util.ArrayList;

public interface UserMangeService extends SuperService {
    ArrayList<MemberDto> getAll();

    void delete(int Id);




}
