package ax.ha.laboration2.inventory.controller;

import ax.ha.laboration2.inventory.model.Product;
import ax.ha.laboration2.inventory.service.EmailService;
import ax.ha.laboration2.inventory.service.ProductService;
import ax.ha.laboration2.inventory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    private final UserService userService;

    private final ProductService productService;

    @Autowired
    public EmailController(final EmailService emailService, final UserService userService,
                           final ProductService productService) {
        this.emailService = emailService;
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping
    public String sendEmail(final Authentication authentication) {
        final Integer userId = userService.getUserId(authentication.getName());
        final List<Product> outOfStock = productService.getProductsWhereAmountIsZero(userId);

        if(outOfStock.isEmpty()) {
            return "redirect:/inventory?emptyList";
        }
        final boolean mailSent = emailService.sendEmail(outOfStock, userService.getUserEmail(userId));
        if(mailSent) {
            return "redirect:/inventory?mailSuccess";
        }
        return "redirect:/inventory?mailError";

    }
}
