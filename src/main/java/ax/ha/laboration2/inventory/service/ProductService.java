package ax.ha.laboration2.inventory.service;

import ax.ha.laboration2.inventory.dao.ProductDao;
import ax.ha.laboration2.inventory.model.Product;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ProductService {

    private final ProductDao productDao;

    public List<Product> getAllProducts() { return productDao.getAllProducts(); }
}
