package ax.ha.laboration2.inventory.dao;

import ax.ha.laboration2.inventory.model.Product;

import java.util.ArrayList;
import java.util.List;

public class TempProductDataAccessService implements ProductDao {

    private List<Product> DB = new ArrayList<>();

    @Override
    public List<Product> getAllProducts() {
        return DB;
    }

    @Override
    public void insertProduct(final Product product) {

    }
}
