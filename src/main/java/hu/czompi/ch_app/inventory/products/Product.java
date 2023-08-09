package hu.czompi.ch_app.inventory.products;

/**
 * An abstract representation of a product.
 */
public abstract class Product {
    /**
     * Product name
     * @return Name of the product
     */
    public abstract String getName();

    /**
     * Product cost
     * @return Price of the product
     */
    public abstract double getPrice();
}