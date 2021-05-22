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

    public ArrayList<RequestDTO> getAllRequest(int indexPage, String key, String statusReqName) throws NamingException, SQLException {
        ArrayList<RequestDTO> list = new ArrayList<>();
        try {
            cn = DBHelper.makeConnection();

            if (cn != null) {
                String sqlCondition = "";
                if (!statusReqName.isEmpty()) {
                    sqlCondition = "and s.statusReqName = ? ";
                }
                String sql = "select requestID, dateBook, req.statusReqID, u.email, r.productID from Requests req, Resources r, Users u, StatusRequest s "
                        + "where req.email = u.email "
                        + "and req.productID = r.productID "
                        + "and req.statusReqID = s.statusReqID "
                        + "and (r.productName like ? or u.email like ? ) " + sqlCondition
                        + "order by  req.requestID desc  OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
                pstm = cn.prepareStatement(sql);
                if (!statusReqName.isEmpty()) {
                    pstm.setString(1, '%' + key + '%');
                    pstm.setString(2, '%' + key + '%');
                    pstm.setString(3, statusReqName);
                    pstm.setInt(4, (indexPage - 1) * MyConstants.QUANITY_ITEM_IN_PAGE);
                    pstm.setInt(5, MyConstants.QUANITY_ITEM_IN_PAGE);
                } else {
                    pstm.setString(1, '%' + key + '%');
                    pstm.setString(2, '%' + key + '%');
                    pstm.setInt(3, (indexPage - 1) * MyConstants.QUANITY_ITEM_IN_PAGE);
                    pstm.setInt(4, MyConstants.QUANITY_ITEM_IN_PAGE);
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

    public int getCountRequests(String key, String statusReqName) throws SQLException, NamingException {
        int count = 0;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String condition = "";
                if (!statusReqName.isEmpty()) {
                    condition = "and s.statusReqName = ? ";
                }
//                String sql = "select  COUNT(*) from Requests req, Resources r, Users u, StatusRequest s where req.email = u.email and req.productID = r.productID\n"
//                        + "and req.statusReqID = s.statusReqID and (r.productName like ? or u.email like ? " + (condition.length() == 0 ? ")" : condition);
                String sql = "select  COUNT(*) from Requests req, Resources r, Users u, StatusRequest s "
                        + "where req.email = u.email "
                        + "and req.productID = r.productID\n"
                        + "and req.statusReqID = s.statusReqID "
                        + "and (r.productName like ? or u.email like ? ) " + condition ;
                pstm = cn.prepareStatement(sql);
                if (!statusReqName.isEmpty()) {
                    pstm.setString(1, '%' + key + '%');
                    pstm.setString(2, '%' + key + '%');
                    pstm.setString(3, statusReqName);
                } else {
                    pstm.setString(1, '%' + key + '%');
                    pstm.setString(2, '%' + key + '%');
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

    public boolean updateStatusRequest(String requestID, int statusID) throws NamingException, SQLException {
        boolean flag = false;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "update Requests set statusReqID = ? "
                        + "where requestID = ?";
                pstm = cn.prepareStatement(sql);
                pstm.setInt(1, statusID);
                pstm.setString(2, requestID);
                flag = pstm.executeUpdate() > 0 ? true : false;
            }
        } finally {
            close();
        }
        return flag;
    }
}
