package hu.czompi.ch_app.inventory.products;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

/**
 * Product details
 */
@Getter
@ToString
public class ProductImpl extends Product {
    @SerializedName("name")
    private final String name;

    @SerializedName("price")
    private final int price;

    /**
     * Create new product
     * @param name Product name
     * @param price Product price
     */
    public ProductImpl(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
