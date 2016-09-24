/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bo;

import bean.User;
import dao.UserDAO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author jacob
 */
public class UserBO {
    UserDAO userDAO = new UserDAO();
    /**
     * Insert user
     * @param user
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public boolean insert(User user ) throws ClassNotFoundException, SQLException {
        return userDAO.insert(user);
    }
    /**
     * update user
     * @param user
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
     public boolean update(User user) throws ClassNotFoundException, SQLException {
         return userDAO.update(user);
     }
     /**
      * get All User
      * @return
      * @throws ClassNotFoundException
      * @throws SQLException 
      */
     public ArrayList<User> getAllUser() throws ClassNotFoundException, SQLException {
         return userDAO.getAllUser();
     }
     /**
      * get user
      * @param username
      * @return
      * @throws ClassNotFoundException
      * @throws SQLException 
      */
     public User getUser(String username) throws ClassNotFoundException, SQLException {
         return userDAO.getUser(username);
     }
     /**
      * delete user
      * @param username
      * @return
      * @throws ClassNotFoundException
      * @throws SQLException 
      */
     public boolean delete(String username) throws ClassNotFoundException, SQLException {
         return userDAO.delete(username);
     }
}
