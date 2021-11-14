package com.company.application.core.security;

import com.company.application.data.employee.entity.Occupation;
import com.company.application.data.employee.entity.Role;
import com.company.application.data.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import java.security.SecureRandom;
import java.util.Optional;

@Controller
public class SecurityController {
    @Autowired
    private SecurityService securityService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public boolean isUserLoggedIn() {
        Authentication authentication = securityService.getAuthentication();
        return authentication != null
                && !(authentication instanceof AnonymousAuthenticationToken)
                && authentication.isAuthenticated();
    }

    public Optional<Role> getCurrentUserRole() {
        return employeeService.getEmployeeRole(getPrincipalName());
    }

    public Optional<Occupation> getCurrentUserOccupation() {
        return employeeService.getEmployeeOccupation(getPrincipalName());
    }

    public String getPrincipalName() {
        Authentication authentication = securityService.getAuthentication();
        if(authentication == null || authentication instanceof AnonymousAuthenticationToken)
            return "";
        return authentication.getName();
    }

    public String getHashedPassword(String password){
        return new BCryptPasswordEncoder(10, new SecureRandom()).encode(password);
    }
}
