package ax.ha.laboration2.inventory.service;

import ax.ha.laboration2.inventory.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts();

    Product getProduct(Integer id);

    List<Product> getProductsInLocation(Integer locationId);

    void addProduct(Product product);

    void deleteProduct(Integer id);
}
