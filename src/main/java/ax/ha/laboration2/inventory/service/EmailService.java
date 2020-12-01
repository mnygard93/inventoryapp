package ax.ha.laboration2.inventory.service;

import ax.ha.laboration2.inventory.model.Product;

import java.util.List;

public interface EmailService {

    boolean sendEmail(List<Product> products, String email);
}
