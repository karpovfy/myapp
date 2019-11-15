package jman.domain;


import javax.persistence.*;

@Entity
@Table(name = "order_reports")
public class OrderReport {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "filename")
    private String filename;

    @Column(name = "owner_id")
    private int ownerID;

    @Column(name = "order_id")
    private int orderID;

    @Column(name = "filetext")
    private String filetext;

    @Column(name = "filepath")
    private String fpath;

    public int getId() {
        return id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public String getFiletext() {
        return filetext;
    }

    public void setFiletext(String filetext) {
        this.filetext = filetext;
    }

    public String getFpath() {
        return fpath;
    }

    public void setFpath(String fpath) {
        this.fpath = fpath;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
}
