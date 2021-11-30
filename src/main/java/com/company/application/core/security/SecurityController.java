package com.company.application.core.security;

import com.company.application.data.employee.entity.Occupation;
import com.company.application.data.employee.entity.Role;
import com.company.application.data.employee.service.EmployeeService;
import com.company.application.domain.employeeprofile.data.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import java.security.SecureRandom;
import java.util.Optional;

/**
 * @author Thorsten Zieres, 1297197
 */
@Controller
public class SecurityController {
    @Autowired
    private SecurityService securityService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public boolean login(String email, String password) throws BadCredentialsException {
        if(email == null || email.isEmpty() ||
                password == null || password.isEmpty())
            return false;

        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(email, password);
        Authentication auth = authenticationManager.authenticate(authReq);
        if(auth == null || auth instanceof AnonymousAuthenticationToken)
            return false;
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
        return true;
    }

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

    public Optional<Employee> getCurrentUser() {
        return employeeService.getEmployeeProfile(getPrincipalName());
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
