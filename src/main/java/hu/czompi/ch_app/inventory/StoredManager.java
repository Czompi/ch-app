package hu.czompi.ch_app.inventory;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * Stored manager instance
 * @param <T> Item type
 */
public interface StoredManager<T> extends Manager<T> {
    /**
     * Represents the file name of the dataset stored by this manager.
     * @return Filename of stored dataset
     */
    Path getFileName();

    /**
     * Represents the default dataset of this manager
     * @return Default dataset
     */
    String getDefaultConfig();

    /**
     * Load file from disk.
     * @throws IOException
     */
    default void load() throws IOException {
        var itemsJson = Files.readString(getFileName());
        setItems(List.of(new Gson().fromJson(itemsJson, getItemClass())));
    }

    /**
     * Save file to disk.
     * @throws IOException
     */
    default void save() throws IOException {
        if (!Files.exists(getFileName(), LinkOption.NOFOLLOW_LINKS)) {
            Files.writeString(getFileName(), getDefaultConfig(), StandardOpenOption.CREATE);
        } else if(!getItems().isEmpty()) {
            Files.writeString(getFileName(), new Gson().toJson(getItems()), StandardOpenOption.WRITE);
        }
    }

    /**
     * Save and load file.
     * @throws IOException
     */
    default void reload() throws IOException {
        save();
        load();
    }

    /**
     * Add item to items list and save it to disk.
     * @param item Item to be added
     */
    @Override
    default void add(T item) {
        Manager.super.add(item);
        try {
            reload();
        } catch (IOException e) {
            Logger logger = LogManager.getLogger(this);
            logger.error(e);
        }
    }
}
