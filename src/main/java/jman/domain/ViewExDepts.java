package jman.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "exdepts_view")
public class ViewExDepts {
    @Id
    @Column(name = "item_dept_id")
    private int itemDeptId;

    @Column(name = "order_id")
    private int orderId;

    @Column(name = "order_item_id")
    private int orderItemId;

    @Column(name = "item_num")
    private String itemNum;

    @Column(name = "item_dsc")
    private String itemDsc;

    @Column(name = "check_time")
    private Date checkTime;

    @Column(name = "fcheck_time")
    private String fcheckTime;

    @Column(name = "exdept_id")
    private int exDeptId;

    @Column(name = "exdeptname")
    private String exDeptName;

    @Column(name = "ordercreator")
    private String orderCreator;

    @Column(name = "control_dept_id")
    private Integer contDeptId;

    @Column(name = "contdeptname")
    private String contDeptName;

    @Column(name = "dayleft")
    private Integer dayLeft;

    @Column(name = "monthleft")
    private Integer monthLeft;

    @Column(name = "order_type_name")
    private String orderTypeName;

    @Column(name = "creator_dept_id")
    private Integer creatorDeptId;

    @Column(name = "order_type")
    private int orderType;

    @Column(name = "document_date")
    private Date documentDate;

    @Column(name = "resp_file")
    private Date respFile;

    @Column(name = "document_num")
    private String documentNum;

    @Column(name = "fresp_file")
    private String frespFile;

    public int getItemDeptId() {
        return itemDeptId;
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

    public int getExDeptId() {
        return exDeptId;
    }

    public void setExDeptId(int exDeptId) {
        this.exDeptId = exDeptId;
    }

    public String getExDeptName() {
        return exDeptName;
    }

    public void setExDeptName(String exDeptName) {
        this.exDeptName = exDeptName;
    }

    public String getOrderCreator() {
        return orderCreator;
    }

    public void setOrderCreator(String orderCreator) {
        this.orderCreator = orderCreator;
    }

    public int getContDeptId() {
        return contDeptId;
    }

    public void setContDeptId(int contDeptId) {
        this.contDeptId = contDeptId;
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

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public int getCreatorDeptId() {
        return creatorDeptId;
    }

    public void setCreatorDeptId(int creatorDeptId) {
        this.creatorDeptId = creatorDeptId;
    }

    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public String getFcheckTime() {
        return fcheckTime;
    }

    public void setFcheckTime(String fcheckTime) {
        this.fcheckTime = fcheckTime;
    }

    public Date getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(Date documentDate) {
        this.documentDate = documentDate;
    }

    public Date getRespFile() {
        return respFile;
    }

    public void setRespFile(Date respFile) {
        this.respFile = respFile;
    }

    public String getDocumentNum() {
        return documentNum;
    }

    public void setDocumentNum(String documentNum) {
        this.documentNum = documentNum;
    }

    public String getFrespFile() {
        return frespFile;
    }

    public void setFrespFile(String frespFile) {
        this.frespFile = frespFile;
    }
}
