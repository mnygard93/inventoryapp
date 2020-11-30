package ax.ha.laboration2.inventory.service;

import ax.ha.laboration2.inventory.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts(Integer userId);

    Product getProduct(Integer id);

    List<Product> getProductsInLocation(Integer locationId);

    List<Product> getProductsWhereAmountIsZero(Integer userId);

    void addProduct(Product product);

    void deleteProduct(Integer id);

    void updateProduct(Product product);

}
