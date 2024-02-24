package id.ac.ui.cs.advprog.eshop.service;
import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;
public interface ProductService {
    public Product create(Product car);
    public List<Product> findAll();
    Product findById(String productId);
    public Product update(String carId, Product product);
    public boolean deleteProductById(String productId);
}
