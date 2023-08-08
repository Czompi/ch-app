package hu.czompi.ch_app.inventory;

import java.util.List;

public interface Manager<T> {
    List<T> getItems();
    void setItems(List<T> newItems);

    default void add(T item) {
        getItems().add(item);
    }
}
