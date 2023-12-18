package dto;

import java.sql.Timestamp;

public class BookmarkGroup {
    private int id;
    private String name;
    private int orderNo;
    private Timestamp createDttm;
    private Timestamp updateDttm;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public Timestamp getCreateDttm() {
        return createDttm;
    }

    public void setCreateDttm(Timestamp createDttm) {
        this.createDttm = createDttm;
    }

    public Timestamp getUpdateDttm() {
        return updateDttm;
    }

    public void setUpdateDttm(Timestamp updateDttm) {
        this.updateDttm = updateDttm;
    }
}
