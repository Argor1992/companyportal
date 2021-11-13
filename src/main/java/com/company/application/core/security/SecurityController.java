package com.company.application.core.security;

import com.company.application.data.employee.controller.EmployeeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import java.security.SecureRandom;

@Controller
public class SecurityController {
    @Autowired
    private SecurityService securityService;
    @Autowired
    private EmployeeController userManagementController;
    @Autowired
    private AuthenticationManager authenticationManager;

    public boolean isUserLoggedIn() {
        Authentication authentication = securityService.getAuthentication();
        return authentication != null
                && !(authentication instanceof AnonymousAuthenticationToken)
                && authentication.isAuthenticated();
    }

    public String getHashedPassword(String password){
        return new BCryptPasswordEncoder(10, new SecureRandom()).encode(password);
    }
}
