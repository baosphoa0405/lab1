/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.resources;

import baotpg.utils.DBHelper;
import baotpg.utils.MyConstants;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class ResourcesDAO {

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

    public ArrayList<ResourceDTO> getAllListReources() throws SQLException, NamingException {
        ArrayList<ResourceDTO> listProducts = new ArrayList<>();
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "select productID, productName, color, categoryID, quanlity, createDate from dbo.Resources";
                pstm = cn.prepareStatement(sql);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    listProducts.add(new ResourceDTO(rs.getString("productID"), rs.getString("productName"), rs.getString("color"), rs.getString("categoryID"), rs.getInt("quanlity"), rs.getDate("createDate")));
                }
            }
        } finally {
            close();
        }
        return listProducts;
    }

    public int getQuanityResouces(String productName, String categoryID, Date dateSearch) throws SQLException, NamingException {
        int count = 0;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String condition = "";
                if (categoryID != null && !categoryID.isEmpty()) {
                    condition = "and categoryID = ? ";
                }
                if (dateSearch != null) {
                    condition = "and createDate = ? ";
                }
                String sql = "select COUNT(*) from dbo.Resources Where productName Like ? " + condition;
                pstm = cn.prepareStatement(sql);
                pstm.setString(1, "%" + productName + "%");
                if (categoryID != null && !categoryID.isEmpty()) {
                    pstm.setString(2, categoryID);
                }
                if (dateSearch != null) {
                    pstm.setDate(2, dateSearch);
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

    public ArrayList<ResourceDTO> getListResourcePagination(String productName, int index, String categoryID, Date dateSearch) throws SQLException, NamingException {
        ArrayList<ResourceDTO> listResources = new ArrayList<>();
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String condition = "";
                if (dateSearch != null && !categoryID.isEmpty()) {
                    condition = "and categoryID = ? and createDate = ? ";
                } else if (dateSearch != null) {
                    condition = "and createDate = ? ";
                } else if (!categoryID.isEmpty()) {
                    condition = "and categoryID = ? ";
                }
                String sql = "select productID, productName, categoryID, color, quanlity, createDate from Resources where productName like ? " + condition
                        + "order by productName asc OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ";
                pstm = cn.prepareStatement(sql);
                pstm.setString(1, "%" + productName + "%");
                if (dateSearch != null && !categoryID.isEmpty()) {
                    pstm.setString(2, categoryID);
                    pstm.setDate(3, dateSearch);
                    pstm.setInt(4, (index - 1) * MyConstants.QUANITY_ITEM_IN_PAGE);
                    pstm.setInt(5, MyConstants.QUANITY_ITEM_IN_PAGE);
                } else if (dateSearch != null) {
                    pstm.setDate(2, dateSearch);
                    pstm.setInt(3, (index - 1) * MyConstants.QUANITY_ITEM_IN_PAGE);
                    pstm.setInt(4, MyConstants.QUANITY_ITEM_IN_PAGE);
                } else if (!categoryID.isEmpty()) {
                    pstm.setString(2, categoryID);
                    pstm.setInt(3, (index - 1) * MyConstants.QUANITY_ITEM_IN_PAGE);
                    pstm.setInt(4, MyConstants.QUANITY_ITEM_IN_PAGE);
                } else {
                    pstm.setInt(2, (index - 1) * MyConstants.QUANITY_ITEM_IN_PAGE);
                    pstm.setInt(3, MyConstants.QUANITY_ITEM_IN_PAGE);
                }
                rs = pstm.executeQuery();
                while (rs.next()) {
                    listResources.add(new ResourceDTO(rs.getString("productID"), rs.getString("productName"), rs.getString("color"), rs.getString("categoryID"), rs.getInt("quanlity"), rs.getDate("createDate")));
                }
            }
        } finally {
            close();
        }
        return listResources;
    }

    public ResourceDTO getDetailResource(String resourceID) throws NamingException, SQLException {
        ResourceDTO resouce = null;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "select productID, productName, color, categoryID, quanlity, createDate from dbo.Resources where productID = ? ";
                pstm = cn.prepareStatement(sql);
                pstm.setString(1, resourceID);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    resouce = new ResourceDTO(rs.getString("productID"), rs.getString("productName"), rs.getString("color"), rs.getString("categoryID"), rs.getInt("quanlity"), rs.getDate("createDate"));
                }
            }
        } finally {
            close();
        }
        return  resouce;
    }
    
    public boolean updateQuanityResource(String productID, int quanity) throws SQLException, NamingException{
        boolean flag = false;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "update dbo.Resources SET quanlity = ? "
                        + "where productID = ? ";
                pstm = cn.prepareStatement(sql);
                pstm.setInt(1, quanity);
                pstm.setString(2, productID);
                flag = pstm.executeUpdate() > 0 ? true : false;
            }
        } finally {
            close();
        }
        return flag;
    }
}
//index = 1 ->  1 -> 3  index*soLuongCuaPhanTuTrongTrang-2 -> index*3
//index = 2 ->  4 -> 6
//index = 3 ->  7 -> 9 
