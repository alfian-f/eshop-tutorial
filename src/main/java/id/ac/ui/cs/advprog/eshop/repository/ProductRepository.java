package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository implements IProductRepository{
    private List<Product> productData = new ArrayList<>();
    public Product create(Product car){
        if(car.getId() == null) {
            UUID uuid = UUID.randomUUID();
            car.setId(uuid.toString());
        }
        productData.add(car);
        return car;
    }

    public Iterator<Product> findAll(){
        return productData.iterator();
    }

    public Product findById(String id) {
        for (Product car : productData) {
            if (car.getId().equals(id)) {
                return car;
            }
        }
        return null;
    }

    public Product update(String id, Product updatedCar) {
        for (Product product : productData) {
            if (product.getId().equals(id)) {
                product.setName(updatedCar.getName());
                product.setQuantity(updatedCar.getQuantity());
                return product;
            }
        }
        return null;
    }

    public boolean delete(String id) {
        return productData.removeIf(product -> product.getId().equals(id));
    }

}
