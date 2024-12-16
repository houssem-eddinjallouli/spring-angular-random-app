package tn.example.thebackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import tn.example.thebackend.config.UserToUserDetails;
import tn.example.thebackend.entities.User;
import tn.example.thebackend.repositories.UserRepository;

import java.util.Optional;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByEmail(username);

        return user.map(UserToUserDetails::new)
                .orElseThrow(()-> new UsernameNotFoundException("user not found " + username));

    }
}
