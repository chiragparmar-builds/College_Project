package com.contactmanager.contact_manager.Configration;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.http.SecurityHeaders;

import com.contactmanager.contact_manager.dao.UserLoginHistoryRepository;
import com.contactmanager.contact_manager.dao.UserRepository;
import com.contactmanager.contact_manager.entities.LastLogin;
import com.contactmanager.contact_manager.entities.User;

public class UserDetailsServiceImple implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserLoginHistoryRepository loginHistoryRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'loadUserByUsername'");
        User user = userRepository.getUserByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User Not Found !!");
        }else{
            LastLogin loginHistory = new LastLogin();
            loginHistory.setLast_login_user(user);
            loginHistory.setLastLogin(LocalDateTime.now());
            loginHistory.setEmail(user.getEmail());
            this.loginHistoryRepository.save(loginHistory);
        }


        // Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication();
        // if (authentication != null && ((org.springframework.security.core.Authentication) authentication).isAuthenticated()) {
        //     LastLogin loginHistory = new LastLogin();
        //     loginHistory.setLast_login_user(user);
        //     loginHistory.setLastLogin(LocalDateTime.now());
        //     loginHistory.setEmail(user.getEmail());
        //     loginHistoryRepository.save(loginHistory);
        //     System.out.println("Hello there");
        // }
        CustomUserDetails customUserDetails = new CustomUserDetails(user);
        return customUserDetails;
    }

}
