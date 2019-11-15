package jman.service;

import jman.domain.ItemDept;

import java.util.List;

public interface ItemDeptService {
    ItemDept getItemDept(int id);
    List<ItemDept> getItemDepts(int id);
    void saveItemDept(ItemDept itemDept);
    void deleteItemDept(ItemDept itemDept);
}
