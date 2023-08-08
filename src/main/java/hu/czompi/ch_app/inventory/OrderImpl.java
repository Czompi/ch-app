package hu.czompi.ch_app.inventory;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OrderImpl implements Order {

    @SerializedName("orderNumber")
    private final int orderNumber;

    @SerializedName("basket")
    private final String[] basket;

    public OrderImpl(int orderNumber, String[] basket) {
        this.orderNumber = orderNumber;
        this.basket = basket;
    }
}
