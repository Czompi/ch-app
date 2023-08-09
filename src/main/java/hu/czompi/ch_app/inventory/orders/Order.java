package hu.czompi.ch_app.inventory.orders;

/**
 * An abstract representation of an order.
 */
public abstract class Order {
    /**
     * Order number
     * @return Id of the order
     */
    public abstract int getOrderNumber();

    /**
     * List of items in the basked.
     * @return Items in the basket
     */
    public abstract String[] getBasket();
}
