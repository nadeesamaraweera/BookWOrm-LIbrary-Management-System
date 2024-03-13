package org.bookmanagement.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bookmanagement.Entity.BorrowBook;
import org.bookmanagement.Entity.Member;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public class BorrowBookDto {

        private int id;

        private int qty;

        private Date dueDate;

        private String status;

        private Timestamp addedDate;

        private Member member;

        public BorrowBook toEntity() {
            return new BorrowBook(
                    id,
                    qty,
                    dueDate,
                    status,
                    addedDate,
                    member,
                    new ArrayList<>()
                    );
        }
    }
