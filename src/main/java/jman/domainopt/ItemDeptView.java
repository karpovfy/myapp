package jman.domainopt;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "itemdeptview")
public class ItemDeptView implements Serializable {
    /*=== MAIN IDS ===*/
    @Id
    @Column(name = "item_dept_id")
    private int itemDeptId;

    @Column(name = "order_item_id")
    private int orderItemId;

    @Column(name = "dept_id")
    private int deptId;

    @Column(name = "order_id")
    private int orderId;

    @Column(name = "control_dept_id")
    private int controlDeptId;

    @Column(name = "order_type")
    private int orderType;

    @Column(name = "creator_dept_id")
    private int orderCreatorDeptID;

    /*=== GRID COLS ===*/
    @Column(name = "order_type_name")
    private String orderTypeName;

    @Column(name = "document_num")
    private String documentNum;

    @Column(name = "document_date")
    private Date documentDate;

    @Column(name = "item_num")
    private String itemNum;

    @Column(name = "item_dsc")
    private String itemDsc;

    @Column(name = "check_time")
    private Date checkTime;

    @Column(name = "mcurdate")
    private Date mcurDate;

    @Column(name = "status")
    private int status;

    @Column(name = "report_date")
    private Date reportDate;

    @Column(name = "dept_name")
    private String deptName;

    @Column(name = "exdept_name")
    private String exdeptName;

    @Column(name = "exdept_type")
    private Integer exdeptType;

    @Column(name = "file_status")
    private Integer fileStatus;

    @Column(name = "order_category")
    private Integer orderCategory;

    @Column(name = "viewed")
    private Short viewed;

    public int getItemDeptId() {
        return itemDeptId;
    }

    public void setItemDeptId(int itemDeptId) {
        this.itemDeptId = itemDeptId;
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

    public int getOrderCreatorDeptID() { return orderCreatorDeptID; }

    public void setOrderCreatorDeptID(int orderCreatorDeptID) { this.orderCreatorDeptID = orderCreatorDeptID; }

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

    public Date getMcurDate() {
        return mcurDate;
    }

    public void setMcurDate(Date mcurDate) {
        this.mcurDate = mcurDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getExdeptName() {
        return exdeptName;
    }

    public void setExdeptName(String exdeptName) {
        this.exdeptName = exdeptName;
    }

    public Integer getExdeptType() { return exdeptType; }

    public void setExdeptType(Integer exdeptType) { this.exdeptType = exdeptType; }

    public Integer getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(Integer fileStatus) {
        this.fileStatus = fileStatus;
    }

    public Integer getOrderCategory() {
        return orderCategory;
    }

    public void setOrderCategory(Integer orderCategory) {
        this.orderCategory = orderCategory;
    }

    public Short getViewed() {
        return viewed;
    }

    public void setViewed(Short viewed) {
        this.viewed = viewed;
    }
}
