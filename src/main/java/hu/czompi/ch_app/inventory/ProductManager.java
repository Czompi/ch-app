package hu.czompi.ch_app.inventory;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class ProductManager implements Manager<ProductImpl> {
    private final Path fileName = Path.of("products.json");
    private final String defaultConfig = new Gson().toJson(Arrays.asList(
            new ProductImpl("A", 55),
            new ProductImpl("B", 20),
            new ProductImpl("C", 60),
            new ProductImpl("D", 10),
            new ProductImpl("E", 45)
    ));
    @Setter private List<ProductImpl> items = new ArrayList<>();

    public ProductManager() {
    }

    public Product get(String name) {
//        for (var p: items) {
//            if(p.getName().equalsIgnoreCase(name))
//                return p;
//        }
//        return null;
        return getItems().stream().filter(x -> x.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }
}
