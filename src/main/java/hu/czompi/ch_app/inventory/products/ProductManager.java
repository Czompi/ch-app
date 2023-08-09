package hu.czompi.ch_app.inventory.products;

import com.google.gson.Gson;
import hu.czompi.ch_app.inventory.StoredManager;
import lombok.Getter;
import lombok.Setter;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class ProductManager implements StoredManager<Product> {
    private final Path fileName = Path.of("products.json");
    private final String defaultConfig = new Gson().toJson(Arrays.asList(
            new ProductImpl("A", 55),
            new ProductImpl("B", 20),
            new ProductImpl("C", 60),
            new ProductImpl("D", 10),
            new ProductImpl("E", 45)
    ));
    @Setter private List<Product> items = new ArrayList<>();

    public ProductManager() {
    }

    @Override
    public Class<ProductImpl[]> getItemClass() {
        return ProductImpl[].class;
    }

    public Product get(String name) {
        return getItems().stream().filter(item -> item.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }
}
