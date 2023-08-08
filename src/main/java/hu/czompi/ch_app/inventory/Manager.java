package hu.czompi.ch_app.inventory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public interface Manager<T> {
    List<T> getItems();
    void setItems(List<T> newItems);
    Path getFileName();
    String getDefaultConfig();

    default void load() throws IOException {
        var itemsJson = Files.readString(getFileName());
        setItems(new Gson().fromJson(itemsJson, new TypeToken<ArrayList<T>>(){}.getType()));
    }

    default void save() throws IOException {
        if (!Files.exists(getFileName(), LinkOption.NOFOLLOW_LINKS)) {
            Files.writeString(getFileName(), getDefaultConfig(), StandardOpenOption.CREATE);
        } else if(!getItems().isEmpty()) {
            Files.writeString(getFileName(), new Gson().toJson(getItems()), StandardOpenOption.WRITE);
        } else {
            //throw new IOException("Unknown state");
        }
    }

    default void reload() throws IOException {
        save();
        load();
    }

    public default void add(T item) {
        getItems().add(item);
    }

}
