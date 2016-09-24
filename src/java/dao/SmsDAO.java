/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import bean.SMS;
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
public class SmsDAO {
    Connection conn = null;
    PreparedStatement pstm = null;
    ResultSet rs = null;
    /**
     * insert SMS
     * @param sms
     * @return boolean
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public boolean insert(SMS sms) throws ClassNotFoundException, SQLException {
        conn =  DBConnection.connection();
        String sql = "INSERT INTO SMS (moId,sender,serviceId,message,operator,commandCode,username,isProcessed) VALUES(?,?,?,?,?,?,?,?)";
        pstm = conn.prepareStatement(sql);
        pstm.setString(1,sms.getMoId());
        pstm.setString(2,sms.getSender());
        pstm.setString(3,sms.getServiceId());
        pstm.setString(4, sms.getMessage());
        pstm.setString(5,sms.getOperator());
        pstm.setString(6, sms.getCommandCode());
        pstm.setString(7,sms.getUsername());
        pstm.setInt(8, sms.getIsProcess());
        try {
         return (pstm.executeUpdate() > 0);
        }finally{
            conn.close();
            pstm.close();
        }
    }
    public boolean insert_error(SMS sms) throws ClassNotFoundException, SQLException {
        conn =  DBConnection.connection();
        String sql = "INSERT INTO SMS_ERROR (moId,sender,serviceId,message,operator,commandCode,username,isProcessed) VALUES(?,?,?,?,?,?,?,?)";
        pstm = conn.prepareStatement(sql);
        pstm.setString(1,sms.getMoId());
        pstm.setString(2,sms.getSender());
        pstm.setString(3,sms.getServiceId());
        pstm.setString(4, sms.getMessage());
        pstm.setString(5,sms.getOperator());
        pstm.setString(6, sms.getCommandCode());
        pstm.setString(7,sms.getUsername());
        pstm.setInt(8, sms.getIsProcess());
        try {
         return (pstm.executeUpdate() > 0);
        }finally{
            conn.close();
            pstm.close();
        }
    }
    /**
     * Delete SMS
     * @param smsId
     * @return boolean
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public boolean deleteBySMSID(int smsId) throws ClassNotFoundException, SQLException {
        conn = DBConnection.connection();
        String sql = "DELETE FROM SMS WHERE smsId = ?";
        pstm = conn.prepareStatement(sql);
        pstm.setInt(1, smsId);
        try{
            return (pstm.executeUpdate() > 0);
        }finally {
            conn.close();
            pstm.close();
        }
        
    }
    /**
     * Update status
     * @param smsId
     * @param status
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public boolean updateStatus(int smsId, int status) throws ClassNotFoundException, SQLException {
        conn = DBConnection.connection();
        String sql = "UPDATE SMS SET isProcessed = ? WHERE smsId = ?";
        pstm = conn.prepareStatement(sql);
        pstm.setInt(1, smsId);
        pstm.setInt(2, status);
        try {
            return (pstm.executeUpdate()> 0);
        }finally {
            conn.close();
            pstm.close();
        }
    }
    /**
     * get all coming SMS
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public ArrayList<SMS> getAllSMS() throws ClassNotFoundException, SQLException {
        ArrayList<SMS> list = null;
        conn = DBConnection.connection();
        String sql = "SELECT * FROM SMS";
        pstm = conn.prepareStatement(sql);
        rs = pstm.executeQuery();
        try {
            if(rs!=null) {
                list = new ArrayList<SMS>();
                while(rs.next()) {
                    SMS sms = new SMS();
                    sms.setCommandCode(rs.getString("commandCode"));
                    sms.setIsProcess(rs.getInt("isProcessed"));
                    sms.setMessage(rs.getString("message"));
                    sms.setMoId(rs.getString("moId"));
                    sms.setOperator(rs.getString("operator"));
                    sms.setSender(rs.getString("sender"));
                    sms.setServiceId(rs.getString("serviceId"));
                    sms.setSmsId(rs.getInt("smsId"));
                    sms.setUsername(rs.getString("username"));
                    list.add(sms);
                }
            }
            return list;
        } finally {
            conn.close();
            pstm.close();
            rs.close();
        }
    }
    /**
     * get SMS by Id
     * @param smsId
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public SMS getSMSbyId(int smsId) throws ClassNotFoundException, SQLException{
        SMS sms = null;
        conn = DBConnection.connection();
        String sql ="SELECT * FROM SMS WHERE smsId = ?";
        pstm = conn.prepareStatement(sql);
        pstm.setInt(1, smsId);
        try {
            rs = pstm.executeQuery();
            if(rs!=null) {
                sms = new SMS();
                while(rs.next()){
                    sms.setCommandCode(rs.getString("commandCode"));
                    sms.setIsProcess(rs.getInt("isProcessed"));
                    sms.setMessage(rs.getString("message"));
                    sms.setMoId(rs.getString("moId"));
                    sms.setOperator(rs.getString("operator"));
                    sms.setSender(rs.getString("sender"));
                    sms.setServiceId(rs.getString("serviceId"));
                    sms.setSmsId(rs.getInt("smsId"));
                    sms.setUsername(rs.getString("username"));
                }
            }
            return sms;
        }finally {
            conn.close();
            pstm.close();
            rs.close();
        }
        
    }
    /**
     * get all SMS not set
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public ArrayList<SMS> getSMSNotSend() throws ClassNotFoundException, SQLException {
        ArrayList<SMS> list = null;
        conn = DBConnection.connection();
        String sql = "SELECT * FROM SMS WHERE isProcessed = ?";
        pstm = conn.prepareStatement(sql);
        pstm.setInt(1,0);
        rs = pstm.executeQuery();
        try {
            if(rs!=null) {
                list = new ArrayList<SMS>();
                while(rs.next()) {
                    SMS sms = new SMS();
                    sms.setCommandCode(rs.getString("commandCode"));
                    sms.setIsProcess(rs.getInt("isProcessed"));
                    sms.setMessage(rs.getString("message"));
                    sms.setMoId(rs.getString("moId"));
                    sms.setOperator(rs.getString("operator"));
                    sms.setSender(rs.getString("sender"));
                    sms.setServiceId(rs.getString("serviceId"));
                    sms.setSmsId(rs.getInt("smsId"));
                    sms.setUsername(rs.getString("username"));
                    list.add(sms);
                }
            }
            return list;
        } finally {
            conn.close();
            pstm.close();
            rs.close();
        }
    }
}
