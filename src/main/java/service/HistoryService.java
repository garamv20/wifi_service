package service;


import dto.History;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryService {
    public static void insertHistory(History history) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DbConnection.getConnection();
            String sql = " insert into search_history (XCOORD, YCOORD, DTTM)" +
                    " values(?, ?, now())";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, history.getxCoord());
            preparedStatement.setDouble(2, history.getyCoord());

            int affected = preparedStatement.executeUpdate();

            if (affected > 0) {
                System.out.println("저장 성공");
            } else {
                System.out.println("저장 실패");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbConnection.closeConnection(connection, preparedStatement, rs);
        }
    }

    public static List<History> selectHistory() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<History> historyList = new ArrayList<>();

        try {
            connection = DbConnection.getConnection();
            String sql = "select * from search_history order by ID DESC";
            preparedStatement = connection.prepareStatement(sql);

            rs = preparedStatement.executeQuery();

            while (rs.next()){
                History history = new History();
                history.setId(rs.getInt("id"));
                history.setxCoord(rs.getDouble("xcoord"));
                history.setyCoord(rs.getDouble("ycoord"));
                history.setDttm(rs.getTimestamp("dttm"));

                historyList.add(history);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbConnection.closeConnection(connection, preparedStatement, rs);
        }

        return historyList;
    }

    public static boolean deleteHistory(String id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        boolean isSuccess = false;

        try {
            connection = DbConnection.getConnection();
            String sql = "delete from search_history where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.valueOf(id));

            int affected = preparedStatement.executeUpdate();
            if (affected > 0){
                isSuccess = true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbConnection.closeConnection(connection, preparedStatement, rs);
        }

        return isSuccess;
    }
}
