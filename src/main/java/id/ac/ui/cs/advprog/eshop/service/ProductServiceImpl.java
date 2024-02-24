package id.ac.ui.cs.advprog.eshop.service;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private IProductRepository productRepository;

    @Override
    public Product create(Product product) {
        productRepository.create(product);
        return product;
    }

    @Override
    public List<Product> findAll() {
        Iterator<Product> carIterator = productRepository.findAll();
        List<Product> allProduct = new ArrayList<>();
        carIterator.forEachRemaining(allProduct::add);
        return allProduct;
    }

    @Override
    public Product findById(String productId){
        Product product = productRepository.findById(productId);
        return product;
    }

    @Override
    public Product update(String productId, Product product){
        productRepository.update(productId, product);
        return product;
    }

    @Override
    public boolean deleteProductById(String productId){
        return productRepository.delete(productId);
    }
}
