package jman.testing;


import javax.persistence.*;

@Entity
@Table(name = "tester")
public class Testmodel {
    @Id
    @GeneratedValue
    @Column(name = "tester_id")
    private int testerId;

    @Column(name = "test_text")
    private String testText;

    public int getTesterId() {
        return testerId;
    }

    public String getTestText() {
        return testText;
    }

    public void setTestText(String testText) {
        this.testText = testText;
    }
}
