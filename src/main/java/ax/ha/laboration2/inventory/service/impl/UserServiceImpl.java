package ax.ha.laboration2.inventory.service.impl;

import ax.ha.laboration2.inventory.auth.MyUserDetails;
import ax.ha.laboration2.inventory.controller.dto.UserRegistrationDto;
import ax.ha.laboration2.inventory.model.User;
import ax.ha.laboration2.inventory.repository.UserMapper;
import ax.ha.laboration2.inventory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final String ROLE = "ROLE_USER";

    private final UserMapper userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(final UserMapper userRepository, final BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(final String userName) throws UsernameNotFoundException {
        Optional<User> user =  userRepository.findByUserName(userName);
        user.orElseThrow(() -> new UsernameNotFoundException(userName + " not found!"));
        return user.map(MyUserDetails::new).get();

    }

    @Override
    public void saveUser(final UserRegistrationDto userRegistrationDto) {
        final User user = new User(userRegistrationDto.getId(), userRegistrationDto.getUserName(),
                bCryptPasswordEncoder.encode(userRegistrationDto.getPassword()), userRegistrationDto.getEmail(),
                true, ROLE);

        userRepository.saveUser(user);
    }

    @Override
    public Integer getUserId(final String userName) {
        return userRepository.getUserId(userName);
    }
}
