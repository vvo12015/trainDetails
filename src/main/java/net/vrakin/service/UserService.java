package net.vrakin.service;

import lombok.extern.slf4j.Slf4j;
import net.vrakin.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        log.debug("call constructor: UserService");
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        UserDetails details = userRepository.findByUsername(s);
        log.debug("call method: loadUserByUsername with s: " + s + " details " + details.toString());

        return userRepository.findByUsername(s);
    }
}
