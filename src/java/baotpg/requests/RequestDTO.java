/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.requests;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class RequestDTO {

    private int requestID, statusReqID;
    private Date dateBook;
    private String email, productID;

    public RequestDTO(int requestID, int statusReqID, Date dateBook, String email, String productID) {
        this.requestID = requestID;
        this.statusReqID = statusReqID;
        this.dateBook = dateBook;
        this.email = email;
        this.productID = productID;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public int getStatusReqID() {
        return statusReqID;
    }

    public void setStatusReqID(int statusReqID) {
        this.statusReqID = statusReqID;
    }

    public Date getDateBook() {
        return dateBook;
    }

    public void setDateBook(Date dateBook) {
        this.dateBook = dateBook;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    @Override
    public String toString() {
        return "RequestDTO{" + "requestID=" + requestID + ", statusReqID=" + statusReqID + ", dateBook=" + dateBook + ", email=" + email + ", productID=" + productID + '}';
    }

}
