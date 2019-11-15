package jman.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "order_files")
public class OrderFile implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "order_file_id")
    private int orderFileId;

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

    public int getOrderFileId() {
        return orderFileId;
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
}
