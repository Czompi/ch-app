package hu.czompi.ch_app.inventory;

import java.util.List;

public interface Manager<T> {
    List<T> getItems();
    void setItems(List<T> newItems);
    Class<T[]> getItemClass();

    default void add(T item) {
        var items = new java.util.ArrayList<>(getItems());
        items.add(item);
        setItems(items);
    }
}
