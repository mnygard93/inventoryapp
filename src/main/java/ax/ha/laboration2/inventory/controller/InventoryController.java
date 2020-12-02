package ax.ha.laboration2.inventory.controller;

import ax.ha.laboration2.inventory.model.Product;
import ax.ha.laboration2.inventory.service.ProductService;
import ax.ha.laboration2.inventory.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class InventoryController {

    private final static Logger LOG = LoggerFactory.getLogger(InventoryController.class);

    private final ProductService productService;

    private final UserService userService;

    @Autowired
    public InventoryController(final ProductService productService, final UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @ModelAttribute("product")
    public Product product() { return new Product(); }

    @GetMapping("/login")
    public String showLoginPage() { return "login"; }

    @GetMapping(value = {"/inventory", "/"})
    public String showStartPage(final Authentication authentication, final ModelMap modelMap) {
        final Integer userId = userService.getUserId(authentication.getName());
        List<Product> products = productService.getProducts(userId);
        List<Product> productsOutOfStock = productService.getProductsWhereAmountIsZero(userId);
        modelMap.addAttribute("productsOutOfStock", productsOutOfStock);
        modelMap.addAttribute("products", products);
        return "index";
    }

    @PostMapping( value = "/inventory", params = {"save"})
    public String saveProduct(@ModelAttribute("product") final Product product,
                              final BindingResult bindingResult,
                              final ModelMap model,
                              final Authentication authentication) {

        if(inputHasErrors(bindingResult)) {
            return "index";
        }
        product.setUserId(userService.getUserId(authentication.getName()));
        productService.addProduct(product);
        model.clear();
        return "redirect:/inventory?saveSuccess";
    }

    @PostMapping(value = "/update", params={"update"})
    public String updateProduct(@ModelAttribute("product") final Product product,
                                final BindingResult bindingResult,
                                final ModelMap model,
                                final Authentication authentication) {

        if(inputHasErrors(bindingResult)) {
            return "index";
        }
        product.setUserId(userService.getUserId(authentication.getName()));
        productService.updateProduct(product);
        model.clear();
        return "redirect:/inventory?updateSuccess";
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
