package hu.czompi.ch_app.inventory.orders;

import hu.czompi.ch_app.inventory.StoredManager;
import lombok.Getter;
import lombok.Setter;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Order manager
 */
@Getter
public class OrderManager implements StoredManager<Order> {
    private final Path fileName = Path.of("orders.json");
    private final String defaultConfig = "[]";
    @Setter private List<Order> items = new ArrayList<>();

    /**
     * Add order to orders list and save it to disk.
     * @param id Order id
     * @param basket Items in the basket
     */
    public void add(Integer id, String[] basket) {
        add(new OrderImpl(id, basket));
    }

    /**
     * Add order to orders list and save it to disk.
     * @return Returns with order id.
     * @param basket Items in the basket
     */
    public int add(String[] basket) {
        int index = getItems().size();
        add(index, basket);
        return index;
    }

    @Override
    public Class<OrderImpl[]> getItemClass() {
        return OrderImpl[].class;
    }
}
