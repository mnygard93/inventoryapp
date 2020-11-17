package ax.ha.laboration2.inventory.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Integer id;

    private  String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private  Date expirationDate;

    private  Integer amount;

    private String location;

}
