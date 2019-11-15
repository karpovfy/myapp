package jman.service;

import jman.domain.Item;

import java.util.List;

public interface ItemService {
    Item getItem(int id);
    List<Item> getOrderItems(int id);
    void saveItem(Item item);
    void deleteItem(Item item);
}
