/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.products;

import baotpg.utils.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class ProductsDAO {

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

    public ArrayList<ProductDTO> getAllListProducts() throws SQLException {
        ArrayList<ProductDTO> listProducts = new ArrayList<>();
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "select productID, productName, color, categoryID, quanlity from dbo.Products";
                pstm = cn.prepareStatement(sql);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    listProducts.add(new ProductDTO(rs.getString("productID"), rs.getString("productName"), rs.getString("color"), rs.getString("categoryID"), rs.getInt("quanlity")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return listProducts;
    }

    public ArrayList<ProductDTO> getAllListsProductByCategory(String categoryID) throws SQLException {
        ArrayList<ProductDTO> listProducts = new ArrayList<>();
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "select productID, productName, color, categoryID, quanlity from dbo.Products "
                        + "where categoryID = ? ";
                pstm = cn.prepareStatement(sql);
                pstm.setString(1, categoryID);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    listProducts.add(new ProductDTO(rs.getString("productID"), rs.getString("productName"), rs.getString("color"), rs.getString("categoryID"), rs.getInt("quanlity")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return listProducts;
    }

    public ArrayList<ProductDTO> getAllListsProductByName(String nameProduct) throws SQLException {
        ArrayList<ProductDTO> listProducts = new ArrayList<>();
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "select productID, productName, color, categoryID, quanlity from dbo.Products "
                        + "where productName LIKE ? ";
                pstm = cn.prepareStatement(sql);
                pstm.setString(1, "%" + nameProduct + "%");
                rs = pstm.executeQuery();
                while (rs.next()) {
                    listProducts.add(new ProductDTO(rs.getString("productID"), rs.getString("productName"), rs.getString("color"), rs.getString("categoryID"), rs.getInt("quanlity")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return listProducts;
    }

}
