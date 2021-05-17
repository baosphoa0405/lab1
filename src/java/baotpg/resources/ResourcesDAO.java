/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.resources;

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

    public ArrayList<ResourceDTO> getAllListReourcesByCategory(String categoryID) throws SQLException, NamingException {
        ArrayList<ResourceDTO> listProducts = new ArrayList<>();
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "select productID, productName, color, categoryID, quanlity from dbo.Resources "
                        + "where categoryID = ? ";
                pstm = cn.prepareStatement(sql);
                pstm.setString(1, categoryID);
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

    public ArrayList<ResourceDTO> getAllListReourceByName(String nameProduct) throws SQLException, NamingException {
        ArrayList<ResourceDTO> listProducts = new ArrayList<>();
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "select productID, productName, color, categoryID, quanlity from dbo.Resources "
                        + "where productName LIKE ? ";
                pstm = cn.prepareStatement(sql);
                pstm.setString(1, "%" + nameProduct + "%");
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

    public int getCountResoucesByName(String keyValue, String categoryID) throws SQLException, NamingException {
        int count = 0;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "select COUNT(*) from dbo.Resources where productName LIKE ? and CategoryID = ?";
                pstm = cn.prepareStatement(sql);
                pstm.setString(1, "%" + keyValue + "%");
                pstm.setString(2, categoryID);
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

    public ArrayList<ResourceDTO> getListResourcePagination(String productName, int pageSize, int index, String CategoryID) throws SQLException, NamingException {
        ArrayList<ResourceDTO> listResources = new ArrayList<>();
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "with X as (select ROW_NUMBER() over (order by productID asc) as productIDClone, productID, productName, color, categoryID, quanlity, createDate from Resources where productName LIKE ? and CategoryID = ?) \n"
                        + "select productID, productName, color, categoryID, quanlity, createDate from X where productIDClone  between ? and ?";
                pstm = cn.prepareStatement(sql);
                pstm.setString(1, "%" + productName + "%");
                pstm.setString(2, CategoryID);
                pstm.setInt(3, (pageSize * index) - 2);
                pstm.setInt(4, index * 3);
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
}
//index = 1 ->  1 -> 3  index*soLuongCuaPhanTuTrongTrang-2 -> index*3
//index = 2 ->  4 -> 6
//index = 3 ->  7 -> 9 
