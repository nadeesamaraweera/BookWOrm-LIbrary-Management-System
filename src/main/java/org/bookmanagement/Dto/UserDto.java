package org.bookmanagement.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private int Id;
    private String full_name;
    private String username;
    private String Password;
    private String Email;
}
