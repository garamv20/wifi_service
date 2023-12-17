package service;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dto.Wifi;

import java.sql.*;
import java.util.ArrayList;

public class WifiService {
    public void dbSelect() {

//        String url = "jdbc:mysql://localhost:3306/wifi_db";
//        String dbUserId = "wifi_user";
//        String dbPassword = "1234";
//
//        try {
//            // 드라이버 로드 (예외처리 필요 -> try catch  or throws)
//            Class.forName("org.mysqldb.jdbc.Driver");
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        // email, kakao, facebook

        try {
//            connection = DriverManager.getConnection(url, dbUserId, dbPassword);  // 예외처리 필요
            connection = DbConnection.getConnection();

            String sql = " SELECT * FROM test" ;

            preparedStatement = connection.prepareStatement(sql);

//            preparedStatement.setString(1, memberTypeValued);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");

                System.out.println(id + ", " + name);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbConnection.closeConnection(connection, preparedStatement, rs);
        }
    }

    /**
     * public_wifi insert
     * @param jsonArray
     * @return
     */
    public static int wifiInsert(JsonArray jsonArray) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        int insertCnt = 0;

        try {
            connection = DbConnection.getConnection();
            String sql = " insert into public_wifi (MGR_NO, WRDOFC, WIFI_NM, ADRES1, ADRES2, INSTL_FLOOR, INSTL_TY, INSTL_MBY, SVC_SE" +
                         " , CMCWR, CNSTC_YEAR, INOUT_DOOR, REMARS3, LAT, LNT, WORK_DTTM)" +
                         " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);

            System.out.println(jsonArray.size());
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                preparedStatement.setString(1, jsonObject.get("X_SWIFI_MGR_NO").getAsString());
                preparedStatement.setString(2, jsonObject.get("X_SWIFI_WRDOFC").getAsString());
                preparedStatement.setString(3, jsonObject.get("X_SWIFI_MAIN_NM").getAsString());
                preparedStatement.setString(4, jsonObject.get("X_SWIFI_ADRES1").getAsString());
                preparedStatement.setString(5, jsonObject.get("X_SWIFI_ADRES2").getAsString());
                preparedStatement.setString(6, jsonObject.get("X_SWIFI_INSTL_FLOOR").getAsString());
                preparedStatement.setString(7, jsonObject.get("X_SWIFI_INSTL_TY").getAsString());
                preparedStatement.setString(8, jsonObject.get("X_SWIFI_INSTL_MBY").getAsString());
                preparedStatement.setString(9, jsonObject.get("X_SWIFI_SVC_SE").getAsString());
                preparedStatement.setString(10, jsonObject.get("X_SWIFI_CMCWR").getAsString());
                preparedStatement.setString(11, jsonObject.get("X_SWIFI_CNSTC_YEAR").getAsString());
                preparedStatement.setString(12, jsonObject.get("X_SWIFI_INOUT_DOOR").getAsString());
                preparedStatement.setString(13, jsonObject.get("X_SWIFI_REMARS3").getAsString());
                preparedStatement.setString(14, jsonObject.get("LAT").getAsString());
                preparedStatement.setString(15, jsonObject.get("LNT").getAsString());
                preparedStatement.setString(16, jsonObject.get("WORK_DTTM").getAsString());
                preparedStatement.addBatch(); // 배치에 담아주기
                preparedStatement.clearParameters(); // 파라미터 clear
            }

            // 배치 실행
            int[] batchRs = preparedStatement.executeBatch();
            insertCnt = batchRs.length;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbConnection.closeConnection(connection, preparedStatement, rs);
        }
        return insertCnt;
    }

    public static ArrayList<Wifi> searchNearWifi(double lat, double lnt) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        ArrayList<Wifi> wifiList = new ArrayList<>();

        try {
            connection = DbConnection.getConnection();

            String sql = "SELECT *"
                    + "    ,round((6371 * acos(cos(radians(?)) * cos(radians(lat)) * cos(radians(lnt) - radians(?)) "
                    + "+ sin(radians(?)) * sin(radians(lat)))), 4) AS distance "
                    + " FROM public_wifi"
                    + " ORDER BY distance"
                    + " limit ? ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, lat);
            preparedStatement.setDouble(2, lnt);
            preparedStatement.setDouble(3, lat);
            preparedStatement.setInt(4, 20);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Wifi wifi = new Wifi();
                wifi.setDistance(rs.getDouble("distance"));
                wifi.setxSwifiMgrNo(rs.getString("mgr_no"));
                wifi.setxSwifiWrdofc(rs.getString("wrdofc"));
                wifi.setxSwifiMainNm(rs.getString("wifi_nm"));
                wifi.setxSwifiAdres1(rs.getString("adres1"));
                wifi.setxSwifiAdres2(rs.getString("adres2"));
                wifi.setxSwifiInstlFloor(rs.getString("instl_floor"));
                wifi.setxSwifiInstlTy(rs.getString("instl_ty"));
                wifi.setxSwifiInstlMby(rs.getString("instl_mby"));
                wifi.setxSwifiSvcSe(rs.getString("svc_se"));
                wifi.setxSwifiCmcwr(rs.getString("cmcwr"));
                wifi.setxSwifiCnstcYear(rs.getString("cnstc_year"));
                wifi.setxSwifiInoutDoor(rs.getString("inout_door"));
                wifi.setxSwifiRemars3(rs.getString("remars3"));
                wifi.setLat(rs.getDouble("lat"));
                wifi.setLnt(rs.getDouble("lnt"));
                wifi.setWorkDttm(rs.getString("work_dttm"));

                wifiList.add(wifi);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbConnection.closeConnection(connection, preparedStatement, rs);
        }
        return wifiList;
    }
//    public static void main(String[] args) {
//        new WifiService().dbSelect();
//    }
}
