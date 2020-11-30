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
    public List<Product> getProducts(Integer userId) {
        return productRepository.getProducts(userId);
    }

    @Override
    public Product getProduct(final Integer id) {
        return productRepository.getProduct(id);
    }

    @Override
    public List<Product> getProductsInLocation(final Integer locationId) {
        return productRepository.getProductsInLocation(locationId);
    }

    @Override
    public List<Product> getProductsWhereAmountIsZero(Integer userId) {
        return productRepository.getProductsWhereAmountIsZero(userId);
    }

    @Override
    public void addProduct(final Product product) {
        productRepository.saveProduct(product);
    }

    @Override
    public void deleteProduct(final Integer id) {
        productRepository.deleteProduct(id);
    }

    @Override
    public void updateProduct(final Product product) {
        productRepository.updateProduct(product);
    }
}
