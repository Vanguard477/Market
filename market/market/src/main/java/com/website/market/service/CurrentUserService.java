package com.website.market.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrentUserService {
    @Value("${security.enabled}")
    private boolean securityEnabled;

    public String getCurrentUserName() {
        if (!securityEnabled) {
            return "Username";
        } else {
            return SecurityContextHolder.getContext().getAuthentication().getName();
        }
    }

}
