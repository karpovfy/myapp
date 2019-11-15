package jman.domain;

import javax.persistence.*;

@Entity
@Table(name = "setting")
public class KipSetting {
    @Id
    @GeneratedValue
    @Column(name = "setting_id")
    private int settingId;

    @Column(name = "path_string")
    private String pathString;

    public int getSettingId() {
        return settingId;
    }

    public String getPathString() {
        return pathString;
    }

    public void setPathString(String pathString) {
        this.pathString = pathString;
    }
}
