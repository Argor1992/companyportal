package com.company.application.domain.login.usecase;

import com.company.application.core.security.SecurityController;
import com.company.application.domain.login.data.LoginData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
public class LoginUseCase {

    @Autowired
    public SecurityController securityController;

    public boolean login(LoginData user) throws BadCredentialsException {
        return securityController.login(user.getEmail(), user.getPassword());
    }
}
