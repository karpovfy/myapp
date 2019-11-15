package jman.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "day_status_card")
public class DayStatusCard implements Serializable {
    @Id
    @Column(name = "dept_id")
    private int deptId;

    @Column(name = "fail_count")
    private int failCount;
    @Column(name = "unsuccess_count")
    private int unsuccessCount;
    @Column(name = "check_count")
    private int checkCount;
    @Column(name = "coming_count")
    private int comingCount;

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public int getFailCount() {
        return failCount;
    }

    public void setFailCount(int failCount) {
        this.failCount = failCount;
    }

    public int getUnsuccessCount() {
        return unsuccessCount;
    }

    public void setUnsuccessCount(int unsuccessCount) {
        this.unsuccessCount = unsuccessCount;
    }

    public int getCheckCount() {
        return checkCount;
    }

    public void setCheckCount(int checkCount) {
        this.checkCount = checkCount;
    }

    public int getComingCount() {
        return comingCount;
    }

    public void setComingCount(int comingCount) {
        this.comingCount = comingCount;
    }
}
