package jman.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "depts_stat")
public class DeptReport {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "dname")
    private String dname;

    @Column(name = "cntall")
    private int allCnt;

    @Column(name = "active")
    private int active;

    @Column(name = "noactive")
    private int noactive;

    @Column(name = "report")
    private int report;

    @Column(name = "report2")
    private int report2;

    public int getAllCnt() {
        return allCnt;
    }

    public void setAllCnt(int allCnt) {
        this.allCnt = allCnt;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getNoactive() {
        return noactive;
    }

    public void setNoactive(int noactive) {
        this.noactive = noactive;
    }

    public int getReport() {
        return report;
    }

    public void setReport(int report) {
        this.report = report;
    }

    public int getReport2() {
        return report2;
    }

    public void setReport2(int report2) {
        this.report2 = report2;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }
}
