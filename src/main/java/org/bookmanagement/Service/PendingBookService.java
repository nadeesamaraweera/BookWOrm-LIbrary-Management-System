package org.bookmanagement.Service;

import org.bookmanagement.Dto.PendingDto;

import java.util.List;

public interface PendingBookService extends SuperService{
    List<PendingDto> getPendingBookDetails();
}
