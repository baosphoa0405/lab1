/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import baotpg.utils.DBHelper;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class UsersDAO {

    private PreparedStatement pstm = null;
    private Connection cn = null;
    private ResultSet rs = null;

    public void close() throws SQLException {
        if (pstm != null) {
            pstm.close();
        }
        if (cn != null) {
            cn.close();
        }
        if (rs != null) {
            rs.close();
        }
    }

    public UserDTO checkLogin(String userID, String password) throws SQLException, NamingException {
        UserDTO user = null;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "SELECT name, phone, password, address, statusID, roleID, createDate, email From [Users]"
                        + "WhERE email = ? And password = ?";
                pstm = cn.prepareStatement(sql);
                pstm.setString(1, userID);
                pstm.setString(2, password);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    user = new UserDTO(rs.getString("name"), rs.getString("phone"), rs.getString("password"), rs.getString("address"),
                            rs.getString("email"), rs.getInt("statusID"), rs.getInt("roleID"), rs.getDate("createDate"));
                }
            }
        } finally {
            close();
        }
        return user;
    }

    public boolean insertUser(UserDTO newUser) throws SQLException, NamingException {
        boolean flag = false;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "INSERT into USERS(name, phone, password, address, statusID, roleID, createDate, email)"
                        + "VALUES(?,?,?,?,?,?,?,?)";
                pstm = cn.prepareStatement(sql);
                pstm.setString(1, newUser.getName());
                pstm.setString(2, newUser.getPhone());
                pstm.setString(3, newUser.getPassword());
                pstm.setString(4, newUser.getAddress());
                pstm.setInt(5, newUser.getStatusID());
                pstm.setInt(6, newUser.getRoleID());
                pstm.setDate(7, newUser.getCreateDate());
                pstm.setString(8, newUser.getEmail());
                flag = pstm.executeUpdate() > 0;
            }
        } finally {
            close();
        }
        return flag;
    }

    public boolean updateUser(String email) throws SQLException, NamingException {
        boolean flag = false;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "update USERS set statusID = 2"
                        + "where email = ?";
                pstm = cn.prepareStatement(sql);
                pstm.setString(1, email);
                flag = pstm.executeUpdate() > 0;
            }
        } finally {
            close();
        }
        return flag;
    }

    public boolean checkDuplicateEmail(String email) throws SQLException, NamingException {
        boolean flag = false;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "select name, phone, password, address, statusID, roleID, createDate, email From [Users] "
                        + "where email = ?";
                pstm = cn.prepareStatement(sql);
                pstm.setString(1, email);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    flag = true;
                }
            }
        } finally {
            close();
        }
        return flag;
    }
}
