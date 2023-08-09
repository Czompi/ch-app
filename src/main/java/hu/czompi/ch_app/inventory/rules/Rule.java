package hu.czompi.ch_app.inventory.rules;

import hu.czompi.ch_app.inventory.products.Product;
import hu.czompi.ch_app.inventory.products.ProductManager;

import java.util.Arrays;
import java.util.List;

/**
 * An abstract representation of a rule.
 */
public abstract class Rule {
    final ProductManager productManager;
    List<String> basket;

    /**
     * @param productManager Single {@link ProductManager} instance
     * @param basket List of products from the current basket.
     */
    public Rule(ProductManager productManager, String[] basket) {
        this.productManager = productManager;
        this.basket = Arrays.stream(basket).toList();
    }

    /**
     * Count match occurrences in the list and returns the found amount.
     * @param list List of items
     * @param match Item, that needs to be matched
     * @return With the amount of {@link T} items in the list
     * @param <T> Data type
     */
    <T> int matchCount(List<T> list, T match) {
        int count = 0;
        for (T item: list) {
            if(item.equals(match)) count++;
        }
        return count;
    }

    /**
     * A product repesentation of matched elements for said rule.
     * @return List of products.
     */
    public abstract List<Product> matched();

    /**
     * Returns the price change for the current rule. <br/>
     * By default, it's a negative (that indicated saved amount) value, but when positive, it costs more.
     * @return Price change amount.
     */
    public abstract double priceChange();
}
