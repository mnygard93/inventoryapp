package ax.ha.laboration2.inventory.dao;

import ax.ha.laboration2.inventory.model.Product;

import java.util.Date;
import java.util.List;

public interface ProductDao {

    List<Product> getAllProducts();

    void insertProduct(Product product);
}
