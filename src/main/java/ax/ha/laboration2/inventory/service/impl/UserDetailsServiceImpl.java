package ax.ha.laboration2.inventory.service.impl;

import ax.ha.laboration2.inventory.auth.MyUserDetails;
import ax.ha.laboration2.inventory.model.User;
import ax.ha.laboration2.inventory.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserMapper userRepository;

    @Autowired
    public UserDetailsServiceImpl(final UserMapper userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user =  userRepository.findByUserName(userName);
        user.orElseThrow(() -> new UsernameNotFoundException(userName + " not found!"));
        return user.map(MyUserDetails::new).get();

    }
}
