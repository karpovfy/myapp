package jman.domain;

import java.util.List;

public class ItemViewRow {
    private int id;
    private ItemView cell;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ItemView getCell() {
        return cell;
    }

    public void setCell(ItemView cell) {
        this.cell = cell;
    }
}
