/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.utils;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Admin
 */
public class DBHelper {

    public static Connection makeConnection() throws NamingException, SQLException {
        Context context = new InitialContext();
        Context tomcat = (Context) context.lookup("java:comp/env");
        DataSource dataSource = (DataSource)tomcat.lookup("DV009");
        Connection con = dataSource.getConnection();
        return con;
        
    }
//    public static void main(String[] args) throws NamingException, SQLException {
//        Connection cn = makeConnection();
//        if (cn != null) {
//            System.out.println("cn is running");
//        }
//    }

}
