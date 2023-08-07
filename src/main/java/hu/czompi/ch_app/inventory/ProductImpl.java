package hu.czompi.ch_app.inventory;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ProductImpl implements Product {
    private String name;
    private int price;

    public ProductImpl(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
