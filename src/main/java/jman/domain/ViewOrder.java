package jman.domain;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "order_view")
public class ViewOrder {
    @Id
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "order_date")
    private Timestamp orderDate;

    @Column(name = "formatted_order_date")
    private String formattedOrderDate;

    @Column(name = "creator_id")
    private int creatorId;

    @Column(name = "creator_dept_id")
    private int creatorDeptId;

    @Column(name = "order_name")
    private String orderName;

    @Column(name = "order_type")
    private int documentType;

    @Column(name = "document_num")
    private String documentNum;

    @Column(name = "document_date")
    private Date documentDate;

    @Column(name = "order_file_id")
    private int orderFileId;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "order_type_name")
    private String orderTypeName;

    @Column(name = "filename")
    private String filename;

    @Column(name = "fpath")
    private String fpath;

    @Column(name = "formatted_date")
    private String formattedDate;

    @Column(name = "deptName")
    private String deptName;

    public int getOrderId() {
        return orderId;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public int getCreatorDeptId() {
        return creatorDeptId;
    }

    public void setCreatorDeptId(int creatorDeptId) {
        this.creatorDeptId = creatorDeptId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public int getDocumentType() {
        return documentType;
    }

    public void setDocumentType(int documentType) {
        this.documentType = documentType;
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

    public int getOrderFileId() {
        return orderFileId;
    }

    public void setOrderFileId(int orderFileId) {
        this.orderFileId = orderFileId;
    }

    public String getOrderTypeName() {
         return orderTypeName;
    }

    public void setOrderTypeName(String orderTypeName) {
        this.orderTypeName = orderTypeName;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getFormattedOrderDate() {
        return formattedOrderDate;
    }

    public void setFormattedOrderDate(String formattedOrderDate) {
        this.formattedOrderDate = formattedOrderDate;
    }

    public String getFpath() {
        return fpath;
    }

    public void setFpath(String fpath) {
        this.fpath = fpath;
    }
}
