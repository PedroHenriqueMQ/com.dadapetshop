package com.dadapetshop.registrations.security;

import com.dadapetshop.registrations.model.Usuario;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AccountDetailsHolder {

    public boolean isUserAuthenticated(String email) {
        if (SecurityContextHolder.getContext().getAuthentication() == null)
            return false;

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof Usuario) {
            Usuario usuario = (Usuario) principal;
            return usuario.getEmail().equals(email);
        }

        return false;
    }
}