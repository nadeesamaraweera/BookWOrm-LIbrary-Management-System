package org.bookmanagement.Service;

import org.bookmanagement.Dto.MemberDto;

public interface RegisterService extends SuperService {
    int Register(MemberDto member);
}