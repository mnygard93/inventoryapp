package ax.ha.laboration2.inventory.service;

import ax.ha.laboration2.inventory.controller.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void saveUser(UserRegistrationDto userRegistrationDto);

    Integer getUserId(String userName);
}
