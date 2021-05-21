/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.requests;

import baotpg.utils.DBHelper;
import baotpg.utils.MyConstants;
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

    public boolean bookingResource(RequestDTO requestBooking) throws NamingException, SQLException {
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

    public ArrayList<RequestDTO> getAllRequest(int indexPage, String email) throws NamingException, SQLException {
        ArrayList<RequestDTO> list = new ArrayList<>();
        try {
            cn = DBHelper.makeConnection();

            if (cn != null) {
                String sqlCondition = "";
                if (email != null) {
                    sqlCondition = "email like ? ";
                }
                String checkWhere = sqlCondition.trim().isEmpty() == false ? "where " + sqlCondition  : "";
                String sql = "select requestID, dateBook, statusReqID, email, productID from Requests " + checkWhere
                        + "order by dateBook desc OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
                pstm = cn.prepareStatement(sql);
                if (email != null) {
                    pstm.setString(1,'%' + email + '%');
                    pstm.setInt(2, (indexPage - 1) * MyConstants.QUANITY_ITEM_IN_PAGE);
                    pstm.setInt(3, MyConstants.QUANITY_ITEM_IN_PAGE);
                } else {
                    pstm.setInt(1, (indexPage - 1) * MyConstants.QUANITY_ITEM_IN_PAGE);
                    pstm.setInt(2, MyConstants.QUANITY_ITEM_IN_PAGE);
                }
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

    public int getCountRequests(String email) throws SQLException, NamingException {
        int count = 0;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String condition = "";
                if (email != null) {
                    condition = "email  like ? ";
                }
                String conditionWhere = condition.isEmpty() == false ? "where " + condition : "";
                String sql = "select COUNT(*) from dbo.Requests " + conditionWhere;
                pstm = cn.prepareStatement(sql);
                if (email != null) {
                    pstm.setString(1, '%' + email + '%');
                }
                rs = pstm.executeQuery();
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }
        } finally {
            close();
        }
        return count;
    }
}
