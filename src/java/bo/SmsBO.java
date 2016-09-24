/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bo;

import bean.SMS;
import dao.SmsDAO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author jacob
 */
public class SmsBO {
    SmsDAO smsDAO = new SmsDAO();
    /**
     * insert SMS
     * @param sms
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public boolean insert(SMS sms) throws ClassNotFoundException, SQLException {
        return smsDAO.insert(sms);
    }
    public boolean insert_error(SMS sms) throws ClassNotFoundException, SQLException {
        return  smsDAO.insert_error(sms);
    }
    /**
     * delete SMS by ID
     * @param smsId
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public boolean deleteBySMSID(int smsId) throws ClassNotFoundException, SQLException {
        return smsDAO.deleteBySMSID(smsId);
    }
    /**
     * update status
     * @param smsId
     * @param status
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public boolean updateStatus(int smsId, int status) throws ClassNotFoundException, SQLException {
        return smsDAO.updateStatus(smsId, status);
    }
    /**
     * get All SMS
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public ArrayList<SMS> getAllSMS() throws ClassNotFoundException, SQLException {
        return smsDAO.getAllSMS();
    }
    /**
     * get SMS by ID
     * @param smsId
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public SMS getSMSbyId(int smsId) throws ClassNotFoundException, SQLException {
        return smsDAO.getSMSbyId(smsId);
    }
    /**
     * get all SMS not send
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
     public ArrayList<SMS> getSMSNotSend() throws ClassNotFoundException, SQLException {
         return smsDAO.getSMSNotSend();
     }
    
}
