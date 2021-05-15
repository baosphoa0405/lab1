/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.users;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class UserDTO {
    private String uersID, name, phone, password, address, email;
    private int statusID, roleID;
    private Date createDate;

    public UserDTO(String uersID, String name, String phone, String password, String address, String email, int statusID, int roleID, Date createDate) {
        this.uersID = uersID;
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.address = address;
        this.email = email;
        this.statusID = statusID;
        this.roleID = roleID;
        this.createDate = createDate;
    }

    public String getUersID() {
        return uersID;
    }

    public void setUersID(String uersID) {
        this.uersID = uersID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

}
