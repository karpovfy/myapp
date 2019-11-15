package jman.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "item_depts")
public class ItemDept implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "item_dept_id")
    private int itemDeptId;

    @Column(name = "order_item_id")
    private int orderItemId;

    @Column(name = "dept_id")
    private int deptId;

    @Column(name = "status")
    private short status;

    @Column(name = "enabled")
    private short enabled;

    @Column(name = "viewed")
    private short viewed;

    public int getItemDeptId() {
        return itemDeptId;
    }

    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public short getEnabled() {
        return enabled;
    }

    public void setEnabled(short enabled) {
        this.enabled = enabled;
    }

    public short getViewed() {
        return viewed;
    }

    public void setViewed(short viewed) {
        this.viewed = viewed;
    }
}
