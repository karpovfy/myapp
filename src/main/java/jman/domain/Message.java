package jman.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "message")
public class Message implements Serializable{
    @Id
    @GeneratedValue
    @Column(name = "msg_id")
    private int msgId;

    @Column(name = "msg_text")
    private String msgText;

    @Column(name = "eval_date")
    private Date evalDate;

    @Column(name = "is_view")
    private Short isView;

    public int getMsgId() {
        return msgId;
    }

    public String getMsgText() {
        return msgText;
    }

    public void setMsgText(String msgText) {
        this.msgText = msgText;
    }

    public Date getEvalDate() {
        return evalDate;
    }

    public void setEvalDate(Date evalDate) {
        this.evalDate = evalDate;
    }

    public Short getIsView() {
        return isView;
    }

    public void setIsView(Short isView) {
        this.isView = isView;
    }
}
