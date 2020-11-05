package ax.ha.laboration2.inventory.api;

import ax.ha.laboration2.inventory.model.Product;
import ax.ha.laboration2.inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public void addProduct(@RequestBody Product product) { productService.addProduct(product); }

    @GetMapping
    public List<Product> getAllProducts() { return productService.getAllProducts(); }
}
