/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.requests;

import baotpg.utils.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class RequestsDAO {

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
    
    public boolean bookingResource(RequestDTO requestBooking) throws NamingException, SQLException{
        boolean isBook = false;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "insert  into Requests(dateBook, statusReqID, email, productID) values(?,?,?,?)";
                pstm = cn.prepareStatement(sql);
                pstm.setDate(1, requestBooking.getDateBook());
                pstm.setInt(2, requestBooking.getStatusReqID());
                pstm.setString(3, requestBooking.getEmail());
                pstm.setString(4, requestBooking.getProductID());
                isBook = pstm.executeUpdate() > 0;
            }
        } finally { 
            close();
        }
        return isBook;
    }
    
    public ArrayList<RequestDTO> getAllRequestFollowStatus(int statusReqID) throws NamingException, SQLException{
        ArrayList<RequestDTO> list = new ArrayList<>();
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "select requestID, dateBook, statusReqID, email, productID from Requests where statusReqID = ? ";
                pstm = cn.prepareStatement(sql);
                pstm.setInt(1, statusReqID);
                rs = pstm.executeQuery();
                while (rs.next()) {                    
                    list.add(new RequestDTO(rs.getInt("requestID"), rs.getInt("statusReqID"), rs.getDate("dateBook"), rs.getString("email"), rs.getString("productID")));
                }
            }
        } finally {
            close();
        }
        return list;
    }
}
