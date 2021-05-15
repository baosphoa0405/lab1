/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.products;

/**
 *
 * @author Admin
 */
public class ProductDTO {

    private String productID, productName, color, categoryID;
    private int quanlity;

    public ProductDTO(String productID, String productName, String color, String categoryID, int quanlity) {
        this.productID = productID;
        this.productName = productName;
        this.color = color;
        this.categoryID = categoryID;
        this.quanlity = quanlity;
    }

    @Override
    public String toString() {
        return "ProductDTO{" + "productID=" + productID + ", productName=" + productName + ", color=" + color + ", categoryID=" + categoryID + ", quanlity=" + quanlity + '}';
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public int getQuanlity() {
        return quanlity;
    }

    public void setQuanlity(int quanlity) {
        this.quanlity = quanlity;
    }

}
