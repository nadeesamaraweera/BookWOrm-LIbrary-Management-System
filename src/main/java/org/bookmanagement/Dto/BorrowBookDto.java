package org.bookmanagement.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bookmanagement.Entity.User;

import java.sql.Date;
import java.sql.Timestamp;

@NoArgsConstructor
    @AllArgsConstructor
    @Data
    public class BorrowBookDto {

        private int id;

        private int qty;

        private Date dueDate;

        private String status;

        private Timestamp addedDate;

        private User member;
}
