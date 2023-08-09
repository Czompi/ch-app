package hu.czompi.ch_app.inventory.rules;

import hu.czompi.ch_app.inventory.products.Product;
import hu.czompi.ch_app.inventory.products.ProductManager;

import java.util.Arrays;
import java.util.List;

/**
 * It is used for calculating savings when there are at least {@value MIN_A_COUNT}x <b>A</b> and one <b>C</b> products in the basket.
 */
public class RuleTwoAOneC extends Rule {
    /**
     * Represents the minimum amount of <b>A</b> product type.
     */
    private static final int MIN_A_COUNT = 2;

    /**
     * Represents the minimum amount of items returned by the {@link #matched()} method.<br />
     * It is used for determining if any {@link #priceChange()} applies.
     */
    private static final int MIN_MATCH_COUNT = 2;

    /**
     * It is used for calculating savings when there are at least {@value MIN_A_COUNT}x <b>A</b> and one <b>C</b> products in the basket.
     * @param productManager Single {@link ProductManager} instance
     * @param basket List of products from the current basket.
     */
    public RuleTwoAOneC(ProductManager productManager, String[] basket) {
        super(productManager, basket);
    }

    /**
     * Returns with <b>A</b> and <b>C</b> products if it has the appropriate amount of <b>A</b>s and has a <b>C</b>.
     * @return Affected product list. If nothing is affected, it returns null.
     */
    @Override
    public List<Product> matched() {
        var countA = matchCount(basket, "A");
        var hasC = basket.contains("C");
        if(countA >= MIN_A_COUNT && hasC) return Arrays.asList(
                productManager.get("A"),
                productManager.get("C")
        );
        return null;
    }

    /**
     * If you have at least {@value MIN_A_COUNT}x <b>A</b> and one <b>C</b> product in one basket, you got one <b>C</b> at half price.<br />
     * Returns with a negative value, which means, that you saved that amount.
     * @return Saved amount.
     */
    @Override
    public double priceChange() {
        var matches = matched();
        return (matches != null && matches.size() >= MIN_MATCH_COUNT) ? -1 * (productManager.get("C").getPrice() / 2) : 0;
    }
}
