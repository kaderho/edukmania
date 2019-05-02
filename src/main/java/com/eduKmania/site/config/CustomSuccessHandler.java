package com.eduKmania.site.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            System.out.println("Can't redirect");
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(Authentication authentication) {
        String url="";

        Collection<? extends GrantedAuthority> authorities =  authentication.getAuthorities();

        List<String> roles = new ArrayList<String>();

        for (GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
        }

        if (isManager(roles)) {
            url = "/manager";
        } else if (isAdmin(roles)) {
        	System.out.println("tesAAAAAAAAA");
            url = "/admin";
        } else if (isApprenant(roles)) {
            url = "/apprenant";
        }else if (isRepetiteur(roles)) {
            url = "/repetiteur";
        } else {
            url="/Access_Denied";
        }

        return url;
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

    private boolean isApprenant(List<String> roles) {
        if (roles.contains("ROLE_APPRENANT")) {
            return true;
        }
        return false;
    }

    private boolean isRepetiteur(List<String> roles) {
        if (roles.contains("ROLE_REPETITEUR")) {
            return true;
        }
        return false;
    }

    private boolean isAdmin(List<String> roles) {
        if (roles.contains("ROLE_ADMIN")) {
            return true;
        }
        return false;
    }

    private boolean isManager(List<String> roles) {
        if (roles.contains("ROLE_MANAGER")) {
            return true;
        }
        return false;
    }
}
