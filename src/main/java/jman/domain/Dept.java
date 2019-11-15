package jman.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "depts")
public class Dept implements Serializable{
    @Id
    @GeneratedValue
    @Column(name = "dept_id")
    private int deptId;

    @Column(name = "dept_name")
    private String deptName;

    @Column(name = "dept_short_name")
    private String deptShortName;

    @Column(name = "dept_number")
    private String deptNumber;

    @Column(name = "parent_id")
    private int parentId;

    @Column(name = "dept_type")
    private int deptType;

    @Column(name = "view_on")
    private int viewOn;

    public int getDeptId() {
        return deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptShortName() {
        return deptShortName;
    }

    public void setDeptShortName(String deptShortName) {
        this.deptShortName = deptShortName;
    }

    public String getDeptNumber() {
        return deptNumber;
    }

    public void setDeptNumber(String deptNumber) {
        this.deptNumber = deptNumber;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getDeptType() {
        return deptType;
    }

    public void setDeptType(int deptType) {
        this.deptType = deptType;
    }

    public int getViewOn() {
        return viewOn;
    }

    public void setViewOn(int viewOn) {
        this.viewOn = viewOn;
    }
}
