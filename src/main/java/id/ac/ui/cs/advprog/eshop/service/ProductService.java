package id.ac.ui.cs.advprog.eshop.service;
import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;
public interface ProductService {
    Product create(Product car);
    List<Product> findAll();
    Product findById(String productId);
    Product update(String carId, Product product);
    boolean deleteProductById(String productId);
}
