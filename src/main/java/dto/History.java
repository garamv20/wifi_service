package dto;

import java.sql.Timestamp;

public class History {
    private int id;
    private double xCoord;
    private double yCoord;
    private Timestamp dttm;

    public History() {}

    public History(double xCoord, double yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getxCoord() {
        return xCoord;
    }

    public void setxCoord(double xCoord) {
        this.xCoord = xCoord;
    }

    public double getyCoord() {
        return yCoord;
    }

    public void setyCoord(double yCoord) {
        this.yCoord = yCoord;
    }

    public Timestamp getDttm() {
        return dttm;
    }

    public void setDttm(Timestamp dttm) {
        this.dttm = dttm;
    }
}
