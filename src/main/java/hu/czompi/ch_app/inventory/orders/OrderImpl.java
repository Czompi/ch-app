package hu.czompi.ch_app.inventory.orders;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

/**
 * Order details
 */
@Getter
@ToString
public class OrderImpl extends Order {

    @SerializedName("orderNumber")
    private final int orderNumber;

    @SerializedName("basket")
    private final String[] basket;

    /**
     * Create a new order
     * @param orderNumber Order id
     * @param basket Current basket
     */
    public OrderImpl(int orderNumber, String[] basket) {
        this.orderNumber = orderNumber;
        this.basket = basket;
    }
}
