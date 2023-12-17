package dto;

import java.sql.Timestamp;

public class Wifi {
    private double distance; // 거리
    private String xSwifiMgrNo;  // 관리번호
    private String xSwifiWrdofc; // 자치구
    private String xSwifiMainNm; // 와이파이명
    private String xSwifiAdres1; // 도로명주소
    private String xSwifiAdres2; // 상세주소
    private String xSwifiInstlFloor; // 설치위치(층)
    private String xSwifiInstlTy; // 설치유형
    private String xSwifiInstlMby; // 설치기관
    private String xSwifiSvcSe; //서비스구분
    private String xSwifiCmcwr; //망종류
    private String xSwifiCnstcYear; // 설치년도
    private String xSwifiInoutDoor; // 실내외 구분
    private String xSwifiRemars3; // wifi접속환경
    private double lat; // 위도
    private double lnt; // 경도
    private String workDttm; // 작업일자

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getxSwifiMgrNo() {
        return xSwifiMgrNo;
    }

    public void setxSwifiMgrNo(String xSwifiMgrNo) {
        this.xSwifiMgrNo = xSwifiMgrNo;
    }

    public String getxSwifiWrdofc() {
        return xSwifiWrdofc;
    }

    public void setxSwifiWrdofc(String xSwifiWrdofc) {
        this.xSwifiWrdofc = xSwifiWrdofc;
    }

    public String getxSwifiMainNm() {
        return xSwifiMainNm;
    }

    public void setxSwifiMainNm(String xSwifiMainNm) {
        this.xSwifiMainNm = xSwifiMainNm;
    }

    public String getxSwifiAdres1() {
        return xSwifiAdres1;
    }

    public void setxSwifiAdres1(String xSwifiAdres1) {
        this.xSwifiAdres1 = xSwifiAdres1;
    }

    public String getxSwifiAdres2() {
        return xSwifiAdres2;
    }

    public void setxSwifiAdres2(String xSwifiAdres2) {
        this.xSwifiAdres2 = xSwifiAdres2;
    }

    public String getxSwifiInstlFloor() {
        return xSwifiInstlFloor;
    }

    public void setxSwifiInstlFloor(String xSwifiInstlFloor) {
        this.xSwifiInstlFloor = xSwifiInstlFloor;
    }

    public String getxSwifiInstlTy() {
        return xSwifiInstlTy;
    }

    public void setxSwifiInstlTy(String xSwifiInstlTy) {
        this.xSwifiInstlTy = xSwifiInstlTy;
    }

    public String getxSwifiInstlMby() {
        return xSwifiInstlMby;
    }

    public void setxSwifiInstlMby(String xSwifiInstlMby) {
        this.xSwifiInstlMby = xSwifiInstlMby;
    }

    public String getxSwifiSvcSe() {
        return xSwifiSvcSe;
    }

    public void setxSwifiSvcSe(String xSwifiSvcSe) {
        this.xSwifiSvcSe = xSwifiSvcSe;
    }

    public String getxSwifiCmcwr() {
        return xSwifiCmcwr;
    }

    public void setxSwifiCmcwr(String xSwifiCmcwr) {
        this.xSwifiCmcwr = xSwifiCmcwr;
    }

    public String getxSwifiCnstcYear() {
        return xSwifiCnstcYear;
    }

    public void setxSwifiCnstcYear(String xSwifiCnstcYear) {
        this.xSwifiCnstcYear = xSwifiCnstcYear;
    }

    public String getxSwifiInoutDoor() {
        return xSwifiInoutDoor;
    }

    public void setxSwifiInoutDoor(String xSwifiInoutDoor) {
        this.xSwifiInoutDoor = xSwifiInoutDoor;
    }

    public String getxSwifiRemars3() {
        return xSwifiRemars3;
    }

    public void setxSwifiRemars3(String xSwifiRemars3) {
        this.xSwifiRemars3 = xSwifiRemars3;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLnt() {
        return lnt;
    }

    public void setLnt(double lnt) {
        this.lnt = lnt;
    }

    public String getWorkDttm() {
        return workDttm;
    }

    public void setWorkDttm(String workDttm) {
        this.workDttm = workDttm;
    }
}

