/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.resources;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class ResourceDTO {

    private String productID, productName, color, categoryID;
    private int quanlity;
    private Date createDate;

    public ResourceDTO(String productID, String productName, String color, String categoryID, int quanlity, Date createDate) {
        this.productID = productID;
        this.productName = productName;
        this.color = color;
        this.categoryID = categoryID;
        this.quanlity = quanlity;
        this.createDate = createDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "ResourceDTO{" + "productID=" + productID + ", productName=" + productName + ", color=" + color + ", categoryID=" + categoryID + ", quanlity=" + quanlity + ", createDate=" + createDate + '}';
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
