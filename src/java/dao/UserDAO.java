/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import bean.User;
import common.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author jacob
 */
public class UserDAO {
    /**
     * Insert new user
     * @param user
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public boolean insert(User user ) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.connection();
        String sql = "INSERT INTO USERS(username) VALUES(?,?)";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, user.getUsername());
        pstm.setString(2, user.getPassword());
        try {
            return (pstm.executeUpdate() > 0);
        } catch (SQLException e) {
            return false;
        }finally {
            if(conn!=null) conn.close();
            if(pstm!=null) pstm.close();
        }
    }
    /**
     * Update user
     * @param user
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public boolean update(User user) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.connection();
        String sql = "UPDATE USERS SET password=? WHERE username = ?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1,user.getPassword());
        pstm.setString(2,user.getUsername());
        try {
            return (pstm.executeUpdate() > 0);
        } catch (Exception e) {
            return false;
        } finally {
            if(conn!=null) conn.close();
            if(pstm!=null) pstm.close();
        }
    }
    /**
     * Get All User
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public ArrayList<User> getAllUser() throws ClassNotFoundException, SQLException {
        ArrayList<User> list = null;
        Connection conn = DBConnection.connection();
        String sql = "SELECT * FROM USERS";
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        if(rs!=null) {
            list = new ArrayList<User>();
            while(rs.next()) {
                User u = new User();
                u.setPassword(rs.getString("password"));
                u.setUsername(rs.getString("username"));
                list.add(u);
            }
        }
        if(conn!=null) conn.close();
        if(pstm!=null) pstm.close();
        if(rs!=null) rs.close();
        return list;
    }
    /**
     * Get user
     * @param username
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public User getUser(String username) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.connection();
        String sql = "SELECT * FROM USERS WHERE username = ?";
        PreparedStatement pstm  = conn.prepareStatement(sql);
        pstm.setString(1,username);
        ResultSet rs = pstm.executeQuery();
        if(rs!=null) {
            User u = new User();
            while(rs.next()) {
                u.setPassword(rs.getString("password"));
                u.setUsername(rs.getString("username"));
            }
            conn.close();
            pstm.close();
            rs.close();
            return u;
        }
        return null;
    }
    /**
     * Delete user
     * @param username
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public boolean delete(String username) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.connection();
        String sql="DELETE FROM USERS WHERE username = ?";
        PreparedStatement pstm  = conn.prepareStatement(sql);
        pstm.setString(1, username);
        try {
            return (pstm.executeUpdate() > 0);
        }catch(SQLException e) {
            return false;
        }finally {
            conn.close();
            pstm.close();
        }
    }
}
