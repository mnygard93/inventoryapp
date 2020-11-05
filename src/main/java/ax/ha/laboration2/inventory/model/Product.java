package ax.ha.laboration2.inventory.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.UUID;

@Getter
public class Product {

    private final UUID id;

    private final String description;

    private final Date expirationDate;

    private final Integer amount;

    public Product(@JsonProperty("id") final UUID id,
                   @JsonProperty("description")final String description,
                   @DateTimeFormat(pattern = "yyyy-MM-dd") @JsonProperty("expiration") final Date expirationDate,
                   @JsonProperty("amount") final Integer amount) {
        this.id = id;
        this.description = description;
        this.expirationDate = expirationDate;
        this.amount = amount;
    }
}
