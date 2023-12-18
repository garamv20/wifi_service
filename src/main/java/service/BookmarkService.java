package service;

import dto.Bookmark;
import dto.BookmarkGroup;
import dto.History;
import dto.Wifi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookmarkService {
    public static int insertBookmark(int wifiId, int groupId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        int result = 0;
        try {
            connection = DbConnection.getConnection();
            String sql = " insert into bookmark (GROUP_ID, WIFI_ID, DTTM)" +
                    " values(?, ?, now())";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, groupId);
            preparedStatement.setInt(2, wifiId);

            result = preparedStatement.executeUpdate();
            System.out.println("북마크 저장");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbConnection.closeConnection(connection, preparedStatement, rs);
        }
        return result;
    }

    public static List<Bookmark> selectBookmark() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Bookmark> bookmarkList = new ArrayList<>();

        try {
            connection = DbConnection.getConnection();

            String sql = "select b.id as id" +
                    	" , b.group_id as group_id" +
                        " , bg.name as group_name" +
                        " , b.wifi_id as wifi_id" +
                        " , w.wifi_nm as wifi_nm" +
                        " , b.dttm as dttm" +
                        " from bookmark b " +
                        " inner join public_wifi w on b.wifi_id = w.id" +
                        " inner join bookmark_group bg on b.group_id = bg.id";
            preparedStatement = connection.prepareStatement(sql);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Bookmark bookmark = new Bookmark();
                bookmark.setId(rs.getInt("id"));
                bookmark.setGroupId(rs.getInt("group_id"));
                bookmark.setGroupName(rs.getString("group_name"));
                bookmark.setWifiId(rs.getInt("wifi_id"));
                bookmark.setWifiName(rs.getString("wifi_nm"));
                bookmark.setDttm(rs.getTimestamp("dttm"));

                bookmarkList.add(bookmark);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbConnection.closeConnection(connection, preparedStatement, rs);
        }

        return bookmarkList;
    }

    public static List<BookmarkGroup> selectBookmarkGroup() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<BookmarkGroup> bookmarkGroupList = new ArrayList<>();

        try {
            connection = DbConnection.getConnection();

            String sql = "select * from bookmark_group order by order_no";
            preparedStatement = connection.prepareStatement(sql);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                BookmarkGroup bookmarkGroup = new BookmarkGroup();
                bookmarkGroup.setId(rs.getInt("id"));
                bookmarkGroup.setName(rs.getString("name"));
                bookmarkGroup.setOrderNo(rs.getInt("order_no"));
                bookmarkGroup.setCreateDttm(rs.getTimestamp("create_dttm"));
                bookmarkGroup.setUpdateDttm(rs.getTimestamp("update_dttm"));
                bookmarkGroupList.add(bookmarkGroup);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbConnection.closeConnection(connection, preparedStatement, rs);
        }

        return bookmarkGroupList;
    }
    public static boolean deleteBookmark(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        boolean isSuccess = false;

        try {
            connection = DbConnection.getConnection();
            String sql = "delete from bookmark where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

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

    public static int insertBookmarkGroup(BookmarkGroup bookmarkGroup) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        int result = 0;

        try {
            connection = DbConnection.getConnection();
            String sql = " insert into bookmark_group (NAME, ORDER_NO, CREATE_DTTM)" +
                    " values(?, ?, now())";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, bookmarkGroup.getName());
            preparedStatement.setInt(2, bookmarkGroup.getOrderNo());

            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbConnection.closeConnection(connection, preparedStatement, rs);
        }
        return result;
    }

    public static int updateBookmarkGroup(BookmarkGroup bookmarkGroup) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        int result = 0;

        try {
            connection = DbConnection.getConnection();
            String sql = "update bookmark_group set name = ?" +
                            " , order_no = ? " +
                            " where id = ? ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, bookmarkGroup.getName());
            preparedStatement.setInt(2, bookmarkGroup.getOrderNo());
            preparedStatement.setInt(3, bookmarkGroup.getId());

            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbConnection.closeConnection(connection, preparedStatement, rs);
        }
        return result;
    }

    public static boolean deleteBookmarkGroup(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        boolean isSuccess = false;

        try {
            connection = DbConnection.getConnection();
            String sql = "delete from bookmark_group where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

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
