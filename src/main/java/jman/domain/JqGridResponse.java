package jman.domain;


import java.util.List;

public class JqGridResponse {
    private Long total;
    private Integer page;
    private Long records;
    private List<JqGridRow> rows;

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

    public List<JqGridRow> getRows() {
        return rows;
    }

    public void setRows(List<JqGridRow> rows) {
        this.rows = rows;
    }
}
