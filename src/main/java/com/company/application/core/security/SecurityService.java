package com.company.application.core.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author Thorsten Zieres, 1297197
 */
@Service
public class SecurityService {
    public Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
