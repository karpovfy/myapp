package jman.domainGrid;

import java.util.List;

public class ItemDeptViewGrid {
    private Long total;
    private Integer page;
    private Long records;
    private List<ItemDeptViewRow> rows;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Long getRecords() {
        return records;
    }

    public void setRecords(Long records) {
        this.records = records;
    }

    public List<ItemDeptViewRow> getRows() {
        return rows;
    }

    public void setRows(List<ItemDeptViewRow> rows) {
        this.rows = rows;
    }
}
