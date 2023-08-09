package hu.czompi.ch_app.inventory;

import java.util.List;

public interface Manager<T> {
    List<T> getItems();
    void setItems(List<T> newItems);
    <TImpl extends T> Class<TImpl[]> getItemClass();

    default void add(T item) {
        var items = new java.util.ArrayList<>(getItems());
        items.add(item);
        setItems(items);
    }
}
