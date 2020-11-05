package ax.ha.laboration2.inventory.dao;

import ax.ha.laboration2.inventory.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository("postgres")
public class ProductDaoImplementation implements ProductDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDaoImplementation(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> getAllProducts() {
        final String query = "SELECT * FROM product";

        return jdbcTemplate.query(query, (resultSet, i) ->{
            final UUID id = UUID.fromString(resultSet.getString("id"));
            final String description = resultSet.getString("description");
            final Date expiration = resultSet.getDate("expiration");
            final Integer amount = resultSet.getInt("amount");
            return new Product(id, description, expiration, amount);
        });
    }

    @Override
    public void insertProduct(final UUID id, final Product product) {

    }
}
