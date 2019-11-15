package jman.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "dept_files")
public class DeptFiles {
    @Id
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

    @Column(name = "dept_id")
    private Integer deptId;

    @Column(name = "item_dept_id")
    private int itemDeptId;

    @Column(name = "fcreate_date")
    private String fcreateDate;

    @Column(name = "ufullname")
    private String ufullName;

    @Column(name = "status")
    private int statusId;

    @Column(name = "status_name")
    private String statusName;

    @Column(name = "status_class")
    private String statusClass;

    @Column(name = "umodfullname")
    private String umodfullName;

    @Column(name = "mod_status_date")
    private String modStatusDate;

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

    public String getFcreateDate() {
        return fcreateDate;
    }

    public void setFcreateDate(String fcreateDate) {
        this.fcreateDate = fcreateDate;
    }

    public String getUfullName() {
        return ufullName;
    }

    public void setUfullName(String ufullName) {
        this.ufullName = ufullName;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusClass() {
        return statusClass;
    }

    public void setStatusClass(String statusClass) {
        this.statusClass = statusClass;
    }

    public String getUmodfullName() {
        return umodfullName;
    }

    public void setUmodfullName(String umodfullName) {
        this.umodfullName = umodfullName;
    }

    public String getModStatusDate() {
        return modStatusDate;
    }

    public void setModStatusDate(String modStatusDate) {
        this.modStatusDate = modStatusDate;
    }

    public String getStatusReason() {
        return statusReason;
    }

    public void setStatusReason(String statusReason) {
        this.statusReason = statusReason;
    }
}
