package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Product implements Item{
    private String productId;
    private String productName;
    private int productQuantity;

    public String getId(){
        return this.getProductId();
    }

    public void setId(String productId) {
        this.setProductId(productId);
    }

    public String getName(){
        return this.getProductName();
    }

    public void setName(String productName) {
        this.setProductName(productName);
    }

    public int getQuantity(){
        return this.getProductQuantity();
    }

    public void setQuantity(int productQuantity) {
        this.setProductQuantity(productQuantity);
    }
}
