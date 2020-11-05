package ax.ha.laboration2.inventory.dao;

import ax.ha.laboration2.inventory.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductDao {

    List<Product> getAllProducts();

    void insertProduct(UUID id, Product product);

    default void addProduct(Product product) {
        final UUID id = UUID.randomUUID();
        insertProduct(id, product);
    }
}
