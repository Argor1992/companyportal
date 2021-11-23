package com.company.application.data.employee.service;

import com.company.application.core.security.UserCredentials;
import com.company.application.data.employee.dtos.EmployeeCredentialsDto;
import com.company.application.data.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

/**
 * @author Thorsten Zieres, 1297197
 */
public class CurrentUserService implements UserDetailsService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (email == null || email.isEmpty())
            throw new UsernameNotFoundException("Could not find user");
        Optional<EmployeeCredentialsDto> user = employeeRepository.findCredentialsByEmail(email);
        if (user.isEmpty())
            throw new UsernameNotFoundException("Could not find user");
        return new UserCredentials(user.get().getEmail(), user.get().getPassword(), user.get().getRole());
    }
}
