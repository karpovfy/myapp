package jman.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "response_files")
public class ReportFile implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "response_file_id")
    private int responseFileId;

    @Column(name = "create_date")
    private Timestamp createDate;

    @Column(name = "genfilename")
    private String genFileName;

    @Column(name = "filename")
    private String filename;

    @Column(name = "fpath")
    private String fpath;

    @Column(name = "creator_id")
    private int creatorId;

    @Column(name = "item_dept_id")
    private int itemDeptId;

    @Column(name = "status")
    private Integer statusId;

    @Column(name = "changer_id")
    private int changerId;

    @Column(name = "status_date")
    private Timestamp statusDate;

    @Column(name = "status_reason")
    private String statusReason;

    public int getResponseFileId() {
        return responseFileId;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getGenFileName() {
        return genFileName;
    }

    public void setGenFileName(String genFileName) {
        this.genFileName = genFileName;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFpath() {
        return fpath;
    }

    public void setFpath(String fpath) {
        this.fpath = fpath;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public int getItemDeptId() {
        return itemDeptId;
    }

    public void setItemDeptId(int itemDeptId) {
        this.itemDeptId = itemDeptId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public int getChangerId() {
        return changerId;
    }

    public void setChangerId(int changerId) {
        this.changerId = changerId;
    }

    public Timestamp getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Timestamp statusDate) {
        this.statusDate = statusDate;
    }

    public String getStatusReason() {
        return statusReason;
    }

    public void setStatusReason(String statusReason) {
        this.statusReason = statusReason;
    }
}
