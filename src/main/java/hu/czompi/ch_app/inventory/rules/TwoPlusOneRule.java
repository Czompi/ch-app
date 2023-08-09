package hu.czompi.ch_app.inventory.rules;

import hu.czompi.ch_app.inventory.products.Product;
import hu.czompi.ch_app.inventory.products.ProductManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * It is used for calculating savings when there are at least {@value MIN_BULK_COUNT} pcs of a single product in the basket.
 */
public class TwoPlusOneRule extends Rule {
    /**
     * Represents the minimum amount of products for a bulk discount.
     */
    private static final int MIN_BULK_COUNT = 3;
    /**
     * A representation of the basket, which has the productId as the key and occurrence of said productId as value.
     */
    Map<String, Long> groups;

    /**
     * It is used for calculating savings when there are at least {@value MIN_BULK_COUNT} pcs of a single product in the basket.
     * @param productManager Single {@link ProductManager} instance
     * @param basket List of products from the current basket.
     */
    public TwoPlusOneRule(ProductManager productManager, String[] basket) {
        super(productManager, basket);
        this.groups = this.basket.stream().collect(Collectors.groupingBy(productId -> productId, Collectors.counting()));
    }

    /**
     * Returns with any products that has {@value MIN_BULK_COUNT} or more occurrence in the basket.
     * @return Affected product list. If nothing is affected, it returns null.
     */
    @Override
    public List<Product> matched() {
        List<Product> productList = new ArrayList<>();

        for (var group : groups.entrySet()) {
            if (group.getValue() >= MIN_BULK_COUNT) productList.add(productManager.get(group.getKey()));
        }

        return productList;
    }

    @Override
    public int priceChange() {
        var discountedProducts = matched();
        int savings = 0;

        for (var product : discountedProducts) {
            savings -= product.getPrice();
        }

        return savings;
    }
}
