package ax.ha.laboration2.inventory.api;

import ax.ha.laboration2.inventory.model.Product;
import ax.ha.laboration2.inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "product", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @RequestMapping(value = "product/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Product> getProduct(@PathVariable final Integer id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @RequestMapping(value = "product/location/{locationId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Product>> getProductsInLocation(@PathVariable final Integer locationId) {
        return ResponseEntity.ok(productService.getProductsInLocation(locationId));
    }

    @RequestMapping(value = "product", method = RequestMethod.POST, consumes = "application/json")
    public void addProduct(@RequestBody final Product product) {
        productService.addProduct(product);
    }

    @RequestMapping(value = "product/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteProduct(@PathVariable final Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
