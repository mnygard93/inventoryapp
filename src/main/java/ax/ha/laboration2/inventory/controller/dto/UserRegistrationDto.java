package ax.ha.laboration2.inventory.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDto {

    private Integer id;

    private String userName;

    private String password;

    private String email;
}
