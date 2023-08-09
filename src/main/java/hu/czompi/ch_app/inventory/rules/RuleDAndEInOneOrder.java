package hu.czompi.ch_app.inventory.rules;

import hu.czompi.ch_app.inventory.products.Product;
import hu.czompi.ch_app.inventory.products.ProductManager;

import java.util.List;

/**
 * It is used for calculating price hike when <b>D</b> and <b>E</b> are in the same order.
 */
public class RuleDAndEInOneOrder extends Rule {
    /**
     * Represents the minimum amount of items returned by the {@link #matched()} method.<br />
     * It is used for determining if any {@link #priceChange()} applies.
     */
    private static final int MIN_MATCH_COUNT = 2;
    /**
     * It is used for calculating price hike when <b>D</b> and <b>E</b> are in the same order.
     * @param productManager Single {@link ProductManager} instance
     * @param basket         List of products from the current basket.
     */
    public RuleDAndEInOneOrder(ProductManager productManager, String[] basket) {
        super(productManager, basket);
    }

    @Override
    public List<Product> matched() {
        if(matchCount(basket, "D") > 0 && matchCount(basket, "E") > 0) {

            List<Product> productList = List.of(
                    productManager.get("D"),
                    productManager.get("E")
            );
            return productList;
        }
        return null;
    }

    @Override
    public double priceChange() {
        var matches = matched();
        if(matches == null || matches.size() < MIN_MATCH_COUNT) return 0;
        double total = 0;
        for (String pid: basket) {
            var prod = productManager.get(pid);
            if (prod == null) continue;
            total += prod.getPrice();
        }
        return total * 0.3D;
    }
}
