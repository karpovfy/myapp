package jman.domain;

import javax.persistence.*;

@Entity
@Table(name = "order_types")
public class OrderType {
    @Id
    @GeneratedValue
    @Column(name = "order_type_id")
    private int orderTypeId;

    @Column(name = "order_type_name")
    private String orderTypeName;

    @Column(name = "sort_order")
    private int sortOrder;

    @Column(name = "active_check")
    private int activeCheck;

    @Column(name = "order_category")
    private int orderCategory;

    public int getOrderTypeId() {
        return orderTypeId;
    }

    public String getOrderTypeName() {
        return orderTypeName;
    }

    public void setOrderTypeName(String orderTypeName) {
        this.orderTypeName = orderTypeName;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public int getActiveCheck() { return activeCheck; }

    public void setActiveCheck(int activeCheck) { this.activeCheck = activeCheck; }

    public int getOrderCategory() {
        return orderCategory;
    }

    public void setOrderCategory(int orderCategory) {
        this.orderCategory = orderCategory;
    }
}
