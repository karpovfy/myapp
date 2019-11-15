package jman.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "items_view")
public class ItemView {
    @Id
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

    @Column(name = "fcheck_time")
    private String fcheckTime;

    @Column(name = "ordercreator")
    private String orderCreator;

    @Column(name = "control_dept_id")
    private int controlDeptId;

    @Column(name = "contdeptname")
    private String contDeptName;

    @Column(name = "dayleft")
    private int dayLeft;

    @Column(name = "monthleft")
    private int monthLeft;

    @Column(name = "order_type_name")
    private String orderTypeName;

    @Column(name = "creator_dept_id")
    private int creatorDeptId;

    @Column(name = "order_type")
    private int orderType;

    @Column(name = "document_date")
    private Date documentDate;


    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
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

    public String getFcheckTime() {
        return fcheckTime;
    }

    public void setFcheckTime(String fcheckTime) {
        this.fcheckTime = fcheckTime;
    }

    public String getOrderCreator() {
        return orderCreator;
    }

    public void setOrderCreator(String orderCreator) {
        this.orderCreator = orderCreator;
    }

    public String getContDeptName() {
        return contDeptName;
    }

    public void setContDeptName(String contDeptName) {
        this.contDeptName = contDeptName;
    }

    public int getDayLeft() {
        return dayLeft;
    }

    public void setDayLeft(int dayLeft) {
        this.dayLeft = dayLeft;
    }

    public int getMonthLeft() {
        return monthLeft;
    }

    public void setMonthLeft(int monthLeft) {
        this.monthLeft = monthLeft;
    }

    public String getOrderTypeName() {
        return orderTypeName;
    }

    public void setOrderTypeName(String orderTypeName) {
        this.orderTypeName = orderTypeName;
    }

    public int getCreatorDeptId() {
        return creatorDeptId;
    }

    public void setCreatorDeptId(int creatorDeptId) {
        this.creatorDeptId = creatorDeptId;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public Date getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(Date documentDate) {
        this.documentDate = documentDate;
    }

    public int getControlDeptId() {
        return controlDeptId;
    }

    public void setControlDeptId(int controlDeptId) {
        this.controlDeptId = controlDeptId;
    }
}
