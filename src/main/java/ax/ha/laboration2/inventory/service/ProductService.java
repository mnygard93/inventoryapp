package ax.ha.laboration2.inventory.service;

import ax.ha.laboration2.inventory.dao.ProductDao;
import ax.ha.laboration2.inventory.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductDao productDao;

    @Autowired
    public ProductService(@Qualifier("postgres") ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> getAllProducts() { return productDao.getAllProducts(); }

    public void addProduct(Product product) { productDao.addProduct(product); }
}
