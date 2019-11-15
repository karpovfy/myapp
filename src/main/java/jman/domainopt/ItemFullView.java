package jman.domainopt;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "itemfullview")
public class ItemFullView implements Serializable {
    /*=== MAIN IDS ===*/
    @Id
    @Column(name = "order_item_id")
    private int orderItemId;

    @Column(name = "order_id")
    private int orderId;

    @Column(name = "control_dept_id")
    private int controlDeptId;

    @Column(name = "order_type")
    private int orderType;

    @Column(name = "creator_id")
    private int creatorId;

    @Column(name = "order_file_id")
    private int orderFileId;

    @Column(name = "order_name")
    private String orderName;

    @Column(name = "order_type_name")
    private String orderTypeName;

    @Column(name = "document_num")
    private String documentNum;

    @Column(name = "document_date")
    private Date documentDate;

    @Column(name = "fpath")
    private String fpath;

    @Column(name = "filename")
    private String filename;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "order_date")
    private Timestamp orderDate;

    @Column(name = "item_num")
    private String itemNum;

    @Column(name = "item_dsc")
    private String itemDsc;

    @Column(name = "check_time")
    private Date checkTime;

    @Column(name = "dept_name")
    private String contDeptName;

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

    public int getControlDeptId() {
        return controlDeptId;
    }

    public void setControlDeptId(int controlDeptId) {
        this.controlDeptId = controlDeptId;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public int getOrderFileId() {
        return orderFileId;
    }

    public void setOrderFileId(int orderFileId) {
        this.orderFileId = orderFileId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderTypeName() {
        return orderTypeName;
    }

    public void setOrderTypeName(String orderTypeName) {
        this.orderTypeName = orderTypeName;
    }

    public String getDocumentNum() {
        return documentNum;
    }

    public void setDocumentNum(String documentNum) {
        this.documentNum = documentNum;
    }

    public Date getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(Date documentDate) {
        this.documentDate = documentDate;
    }

    public String getFpath() {
        return fpath;
    }

    public void setFpath(String fpath) {
        this.fpath = fpath;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
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

    public String getContDeptName() {
        return contDeptName;
    }

    public void setContDeptName(String contDeptName) {
        this.contDeptName = contDeptName;
    }
}
