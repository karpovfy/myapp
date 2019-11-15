package jman.serviceopt;

import jman.domainopt.ItemDeptView;
import java.util.List;

public interface ItemDeptViewService {
    List<ItemDeptView> getList(String where, int frow, int rows);
    List<ItemDeptView> getListItem(int orderItemId);
    List<ItemDeptView> getListItemOrder(int orderItemId);
    Long getCount(String where);
}
