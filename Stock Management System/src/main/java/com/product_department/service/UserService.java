package com.product_department.service;

import com.product_department.dto.UserDto;
import com.product_department.entity.UserCredential;
import com.product_department.repository.UserCredentialRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@Slf4j
public class UserService {

    private UserCredentialRepo userCredentialRepo;
    private BCryptPasswordEncoder passwordEncoder;

    public UserService(UserCredentialRepo userCredentialRepo, BCryptPasswordEncoder passwordEncoder) {
        this.userCredentialRepo = userCredentialRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public String createAccount(UserDto userDto) {

        String encodedPassword = passwordEncoder.encode(userDto.getPassword());

        System.out.println(userDto.getPassword());

        UserCredential saved = userCredentialRepo.save(new UserCredential(0, userDto.getName(),
                                                        encodedPassword,userDto.getRole(), userDto.getAuthority()));
        return saved != null? userDto.getName()+" account created sucessfully" : "Failed to create an account";
    }
}
