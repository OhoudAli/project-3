package com.example.project3.Service;


import com.example.project3.Model.User;
import com.example.project3.Repository.AuthRepository;
import com.example.project3.Repository.CustomerRepository;
import com.example.project3.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {


    private final AuthRepository authRepository;

    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;

    public void createUser(UserDTO userDTO){
        User user = new User();
        String hashPassword = new BCryptPasswordEncoder().encode(userDTO.getPassword());

        user.setUsername(userDTO.getUsername());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(hashPassword);
        userDTO.setRole("ADMIN");
        user.setRole(userDTO.getRole());

        authRepository.save(user);




    }


}
