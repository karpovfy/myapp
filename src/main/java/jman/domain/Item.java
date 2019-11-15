package jman.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "order_items")
public class Item implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private int orderItemId;

    @Column(name = "order_id")
    private int orderId;

    @Column(name = "item_num")
    private String itemNum;

    @Column(name = "item_dsc")
    private String itemDsc;

    @Column(name = "check_time")
    private Date checkTime;

    @Column(name = "control_dept_id")
    private Integer controlDeptId;

    @Column(name = "order_item_date")
    private Timestamp orderItemDate;

    @Column(name = "creator_id")
    private int creatorId;

    public int getOrderItemId() {
        return orderItemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getItemNum() {
        return itemNum;
    }

    public void setItemNum(String itemNum) {
        this.itemNum = itemNum;
    }

    public String getItemDsc() {
        return itemDsc;
    }

    public void setItemDsc(String itemDsc) {
        this.itemDsc = itemDsc;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public Integer getControlDeptId() {
        return controlDeptId;
    }

    public void setControlDeptId(Integer controlDeptId) {
        this.controlDeptId = controlDeptId;
    }

    public Timestamp getOrderItemDate() {
        return orderItemDate;
    }

    public void setOrderItemDate(Timestamp orderItemDate) {
        this.orderItemDate = orderItemDate;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }
}
