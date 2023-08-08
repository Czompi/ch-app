package hu.czompi.ch_app.inventory;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public interface StoredManager<T> extends Manager<T> {
    Path getFileName();
    String getDefaultConfig();

    default void load() throws IOException {
        var itemsJson = Files.readString(getFileName());
        setItems(List.of(new Gson().fromJson(itemsJson, getItemClass())));
    }

    default void save() throws IOException {
        if (!Files.exists(getFileName(), LinkOption.NOFOLLOW_LINKS)) {
            Files.writeString(getFileName(), getDefaultConfig(), StandardOpenOption.CREATE);
        } else if(!getItems().isEmpty()) {
            Files.writeString(getFileName(), new Gson().toJson(getItems()), StandardOpenOption.WRITE);
        }
    }

    default void reload() throws IOException {
        save();
        load();
    }

}
