package ax.ha.laboration2.inventory.model;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class Product {

    private final UUID id;

    private final String description;

    private final Date expirationDate;

    private final Integer amount;
}
