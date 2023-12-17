package service;

import java.sql.*;

public class DbConnection {
//    private static Connection conn;

//    private DbConnection() {}

    public static Connection getConnection(){

        String jdbc = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/wifi_db";
        String uid = "wifi_user";
        String pwd = "1234";

        Connection conn = null;

        try {
            Class.forName(jdbc);
            conn = DriverManager.getConnection(url, uid, pwd);
        } catch (ClassNotFoundException e ) {
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return conn;
    }

    public static void closeConnection(Connection conn, PreparedStatement ps, ResultSet rs){
        try {
            System.out.println("closeConnection");
            if (rs != null && !rs.isClosed()) {
                rs.close();
                System.out.println("rs close");
            }
            if (ps != null && !ps.isClosed()){
                ps.close();
                System.out.println("ps close");
            }
            if (conn != null && !conn.isClosed()){
                conn.close();
                System.out.println("conn close");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
