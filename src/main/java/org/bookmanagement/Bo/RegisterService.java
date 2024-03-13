package org.bookmanagement.Bo;

import org.bookmanagement.Dto.MemberDto;

public interface RegisterService extends SuperService {
    int Register(MemberDto member);
}
