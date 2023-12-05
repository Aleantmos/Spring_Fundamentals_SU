package com.dictionaryapp.model.dto;

import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDTO {

    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters (inclusive 3 and 20).")
    private String username;
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters (inclusive 3 and 20).")
    private String password;
}
