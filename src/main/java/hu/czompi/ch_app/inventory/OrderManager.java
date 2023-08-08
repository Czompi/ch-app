package hu.czompi.ch_app.inventory;

import lombok.Getter;
import lombok.Setter;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Getter
public class OrderManager implements Manager<OrderImpl>{
    private final Path fileName = Path.of("orders.json");
    private final String defaultConfig = "[]";
    @Setter private List<OrderImpl> items = new ArrayList<>();

    public void add(Integer id, String[] order) {
        add(new OrderImpl(id, order));
    }
    public int add(String[] order) {
        int index = getItems().size();
        add(index, order);
        return index;
    }
}
