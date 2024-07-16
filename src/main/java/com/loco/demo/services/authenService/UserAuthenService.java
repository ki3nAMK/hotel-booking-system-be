package com.loco.demo.services.authenService;

import com.loco.demo.AuthenModel.User;
import com.loco.demo.repository.AuthenRepo.UserAuthenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthenService implements UserDetailsService {

    private final UserAuthenRepo userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserAuthenService(UserAuthenRepo userRepository,
                             PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> applicationUser = userRepository.findUserByUsername(username);
        if(applicationUser.isEmpty()) throw new UsernameNotFoundException("NO USERNAME FOUND IN DATABASE !!!") ;
        return applicationUser.get() ;
    }

}
