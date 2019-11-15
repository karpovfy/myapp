package jman.domain;

import java.util.List;

public class ItemViewGrid {
    private Long total;
    private Integer page;
    private Long records;
    private List<ItemViewRow> rows;

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

    public List<ItemViewRow> getRows() {
        return rows;
    }

    public void setRows(List<ItemViewRow> rows) {
        this.rows = rows;
    }
}
