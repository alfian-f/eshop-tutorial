package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Car implements Item {
    private String carId;
    private String carName;
    private String carColor;
    private int carQuantity;

    public String getId(){
        return this.getCarId();
    }

    public void setId(String carId) {
        this.setCarId(carId);
    }

    public String getName(){
        return this.getCarName();
    }

    public void setName(String carName) {
        this.setCarName(carName);
    }

    public int getQuantity(){
        return this.getCarQuantity();
    }

    public void setQuantity(int carQuantity) {
        this.setCarQuantity(carQuantity);
    }
}
