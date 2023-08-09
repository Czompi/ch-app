package hu.czompi.ch_app.inventory;

import java.util.List;

/**
 * Base manager instance
 * @param <T> Item type
 */
public interface Manager<T> {
    /**
     * Returns the item list stored in the manager
     * @return
     */
    List<T> getItems();

    /**
     * Overrides the item list stored in the manager
     * @return
     */
    void setItems(List<T> newItems);

    /**
     * Returns with item implementation class.
     * @return Class of {@code TImpl}
     * @param <TImpl> {@code T}'s implementation
     */
    <TImpl extends T> Class<TImpl[]> getItemClass();

    /**
     * Add item to items list.
     * @param item Item to be added
     */
    default void add(T item) {
        var items = new java.util.ArrayList<>(getItems());
        items.add(item);
        setItems(items);
    }
}
