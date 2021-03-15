package com.marco.apps.services;

import com.marco.apps.models.entity.CustomUserDetails;
import com.marco.apps.models.entity.User;
import com.marco.apps.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService  {

//    @Autowired
//    private UserRepository userRepository;
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//            User user = userRepository.findByEmail(email);
//            if(user == null){
//                throw new UsernameNotFoundException("User not found");
//            }
//            return new CustomUserDetails(user);
//
//
//
//    }
}
