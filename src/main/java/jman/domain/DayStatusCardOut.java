package jman.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "day_status_card_out")
public class DayStatusCardOut implements Serializable {
    @Id
    @Column(name = "dept_id")
    private int deptId;

    @Column(name = "check_count")
    private int checkCount;

    @Column(name = "success_count")
    private int successCount;

    @Column(name = "nocheck_count")
    private int nocheckCount;


    @Column(name = "cancel_count")
    private int cancelCount;

    public int getDeptId() {
        return deptId;
    }

    public int getCheckCount() {
        return checkCount;
    }

    public void setCheckCount(int checkCount) {
        this.checkCount = checkCount;
    }

    public int getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(int successCount) {
        this.successCount = successCount;
    }

    public int getNocheckCount() {
        return nocheckCount;
    }

    public void setNocheckCount(int nocheckCount) {
        this.nocheckCount = nocheckCount;
    }

    public int getCancelCount() {
        return cancelCount;
    }

    public void setCancelCount(int cancelCount) {
        this.cancelCount = cancelCount;
    }
}
