package ax.ha.laboration2.inventory.repository;

import ax.ha.laboration2.inventory.model.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    List<Product> getProducts();

    Product getProduct(Integer id);

    List<Product> getProductsInLocation(Integer locationId);

    void saveProduct(Product product);

    void deleteProduct(Integer id);
}
