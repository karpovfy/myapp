package jman.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "order_date")
    private Timestamp orderDate;

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
}
