package net.vrakin.service;

import net.vrakin.model.ShowContentsInList;
import net.vrakin.model.User;
import net.vrakin.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService extends GeneralAbstractService<User> implements UserDetailsService{

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s);
    }


    @Override
    protected void init() {
        this.repo = userRepository;
    }

    @Override
    public boolean checkUniqueName(String name) {
        return userRepository.findByUsername(name) != null;
    }
}
