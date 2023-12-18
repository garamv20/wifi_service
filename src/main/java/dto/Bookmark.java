package dto;

import java.sql.Timestamp;

public class Bookmark {
    private int id;
    private int groupId;
    private String groupName;
    private int wifiId;
    private String wifiName;
    private Timestamp dttm;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getWifiName() {
        return wifiName;
    }

    public void setWifiName(String wifiName) {
        this.wifiName = wifiName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getWifiId() {
        return wifiId;
    }

    public void setWifiId(int wifiId) {
        this.wifiId = wifiId;
    }

    public Timestamp getDttm() {
        return dttm;
    }

    public void setDttm(Timestamp dttm) {
        this.dttm = dttm;
    }
}
