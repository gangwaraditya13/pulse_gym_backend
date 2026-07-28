package com.pluse.gym_admin.service.Impl;

import com.pluse.gym_admin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.pluse.gym_admin.entity.User user = userRepository.findByUserName(username).orElse(null);

        if(user != null){
            UserDetails userDetails =org.springframework.security.core.userdetails.User
                    .builder()
                    .username(user.getUserName())
                    .password(user.getPassword()).roles(user.getRole().toArray(new String[0])).build();
            return userDetails;
        }
        throw new UsernameNotFoundException("User not found"+username);
    }
}
