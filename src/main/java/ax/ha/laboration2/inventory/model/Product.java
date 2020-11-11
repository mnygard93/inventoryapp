package ax.ha.laboration2.inventory.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class Product {

    private Integer id;

    private  String description;

    private  Date expirationDate;

    private  Integer amount;

    private  Location location;

}
