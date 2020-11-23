package ax.ha.laboration2.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {

    private Integer userId;

    private String userName;

    private String password;

    private boolean isActive;

    private String roles;
}
