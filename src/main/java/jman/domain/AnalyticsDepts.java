package jman.domain;

import javax.persistence.*;

@Entity
@Table(name = "dept_main_analytics")
public class AnalyticsDepts {
    @Id
    @Column(name = "dept_id")
    private int deptId;

    @Column(name = "dept_name")
    private String deptName;

    @Column(name = "items")
    private int allItem;

    @Column(name = "suc_items")
    private int allFinish;

    @Column(name = "fut_items")
    private int allActive;

    @Column(name = "fail_items")
    private int allFail;

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public int getAllItem() {
        return allItem;
    }

    public void setAllItem(int allItem) {
        this.allItem = allItem;
    }

    public int getAllFinish() {
        return allFinish;
    }

    public void setAllFinish(int allFinish) {
        this.allFinish = allFinish;
    }

    public int getAllActive() {
        return allActive;
    }

    public void setAllActive(int allActive) {
        this.allActive = allActive;
    }

    public int getAllFail() {
        return allFail;
    }

    public void setAllFail(int allFail) {
        this.allFail = allFail;
    }
}
