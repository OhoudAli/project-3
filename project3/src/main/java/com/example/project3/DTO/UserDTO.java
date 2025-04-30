package com.example.project3.DTO;


import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor

public class UserDTO {


    @NotNull
    @Size(min = 4, max = 10)
    private String username;

    @NotNull
    @Size(min = 6)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$", message = "password must contain letters ,digits and special character")
    private String password;

    @NotNull
    @Size(min = 2, max = 20)
    private String name;

    @NotNull
    @Email
    private String email;

    private String role;
}
