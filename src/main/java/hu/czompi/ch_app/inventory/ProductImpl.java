package hu.czompi.ch_app.inventory;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ProductImpl implements Product {
    private String id;
    private String name;
    private int price;

    public ProductImpl(String id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
