package ax.ha.laboration2.inventory.controller;

import ax.ha.laboration2.inventory.model.Location;
import ax.ha.laboration2.inventory.model.Product;
import ax.ha.laboration2.inventory.service.LocationService;
import ax.ha.laboration2.inventory.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class InventoryController {

    private static Logger LOG = LoggerFactory.getLogger(InventoryController.class);
    private final ProductService productService;

    private final LocationService locationService;

    @Autowired
    public InventoryController(final ProductService productService, final LocationService locationService) {
        this.productService = productService;
        this.locationService = locationService;
    }

    @ModelAttribute("allProducts")
    public List<Product> allProducts() {
        return productService.getProducts();
    }

    @ModelAttribute("outOfProducts")
    public List<Product> outOfProducts() {
        return productService.getProductsWhereAmountIsZero();
    }

    @GetMapping("/inventory")
    public String showStartPage(final ModelMap model) {
        model.addAttribute("product", new Product());
        LOG.info("Starting...");
        return "index";
    }

    @PostMapping( value = "/inventory", params = {"save"})
    public String saveProduct(@ModelAttribute("product") final Product product,
                              final BindingResult bindingResult,
                              final ModelMap model) {

        if(inputHasErrors(bindingResult)) {
            return "index";
        }
        productService.addProduct(product);
        LOG.info("Product saved!");
        model.clear();
        return "redirect:/inventory";
    }

    @PostMapping(value = "/update", params={"update"})
    public String updateProduct(@ModelAttribute("product") final Product product,
                                final BindingResult bindingResult,
                                final ModelMap model) {

        if(inputHasErrors(bindingResult)) {
            return "index";
        }

        productService.updateProduct(product);
        LOG.info("Product updated!");
        model.clear();
        return "redirect:/inventory";
    }

    private boolean inputHasErrors(BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            LOG.error("Error creating Product");
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors ) {
                LOG.error(error.getObjectName() + " - " + error.getDefaultMessage());
            }
            return true;
        }
        return false;
    }
}
