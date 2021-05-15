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

    public UserDTO checkLogin(String userID, String password) throws SQLException {
        UserDTO user = null;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "Select userID, name, phone, password, address, statusID, roleID, createDate, email From [User]"
                        + "Where userID = ? And password = ?";
                pstm = cn.prepareStatement(sql);
                pstm.setString(1, userID);
                pstm.setString(2, password);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    user = new UserDTO(rs.getString("userID"), rs.getString("name"), rs.getString("phone"), rs.getString("password"), rs.getString("address"),
                            rs.getString("email"), rs.getInt("statusID"), rs.getInt("roleID"), rs.getDate("createDate"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            close();
        }
        return user;
    }

}
