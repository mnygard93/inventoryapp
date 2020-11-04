package ax.ha.laboration2.inventory.model;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class Product {

    private final Long id;

    private final String description;

    private final Date dateBought;

    private final Date expirationDate;

    private final Integer amount;
}
