/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jacob
 */
public class DBConnection {
    public static Connection connection() throws ClassNotFoundException, SQLException{
        Class.forName( "com.microsoft.sqlserver.jdbc.SQLServerDriver" );
        Connection con = DriverManager.getConnection( "jdbc:sqlserver://222.255.167.6:1433;DatabaseName=sms;user=sa;Password=CTnEt1411");
        return con;
    }
}
