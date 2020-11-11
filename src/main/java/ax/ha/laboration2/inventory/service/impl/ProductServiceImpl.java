package ax.ha.laboration2.inventory.service.impl;

import ax.ha.laboration2.inventory.model.Product;
import ax.ha.laboration2.inventory.repository.ProductMapper;
import ax.ha.laboration2.inventory.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productRepository;

    public ProductServiceImpl(final ProductMapper productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.getProducts();
    }

    @Override
    public Product getProduct(Integer id) {
        return productRepository.getProduct(id);
    }

    @Override
    public List<Product> getProductsInLocation(Integer locationId) {
        return productRepository.getProductsInLocation(locationId);
    }

    @Override
    public void addProduct(Product product) {
        productRepository.saveProduct(product);
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteProduct(id);
    }
}
