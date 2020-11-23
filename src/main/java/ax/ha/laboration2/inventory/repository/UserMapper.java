package ax.ha.laboration2.inventory.repository;

import ax.ha.laboration2.inventory.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {

    Optional<User> findByUserName(String userName);
}
