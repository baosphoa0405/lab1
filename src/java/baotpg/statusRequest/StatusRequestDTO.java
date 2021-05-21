/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.statusRequest;

/**
 *
 * @author Admin
 */
public class StatusRequestDTO {

    private int statusReqID;
    private String statusReqName;

    public StatusRequestDTO(int statusReqID, String statusReqName) {
        this.statusReqID = statusReqID;
        this.statusReqName = statusReqName;
    }

    @Override
    public String toString() {
        return "StatusRequest{" + "statusReqID=" + statusReqID + ", statusReqName=" + statusReqName + '}';
    }

    public int getStatusReqID() {
        return statusReqID;
    }

    public void setStatusReqID(int statusReqID) {
        this.statusReqID = statusReqID;
    }

    public String getStatusReqName() {
        return statusReqName;
    }

    public void setStatusReqName(String statusReqName) {
        this.statusReqName = statusReqName;
    }

}
