package jman.service;

import jman.domain.ItemView;
import java.util.List;

public interface ItemViewService {
    List<ItemView> getItems(int deptId);
    Long getItemCount(int deptId);

    List<ItemView> getItemsWhere(String where, int frow, int rows);
    Long getItemCountWhere(String where);
}
