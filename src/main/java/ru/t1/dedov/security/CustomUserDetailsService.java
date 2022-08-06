package ru.t1.dedov.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.t1.dedov.model.entity.User;
import ru.t1.dedov.model.repository.UserRepository;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(s);
        if(user.isPresent()){
            User existingUser = user.get();
            return org.springframework.security.core.userdetails.
                    User
                        .withUsername(existingUser.getUsername())
                        .password(existingUser.getPassword())
                        .authorities("USER")
                        .build();
        } else {
            throw new UsernameNotFoundException(String.format("Username[%s] not found", s));
        }
    }
}
