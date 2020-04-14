package thangdh.dtos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author dhtha
 */
public class UserDTO {

    private String email;
    private String name;
    private String password;
    private String roleID;
    private String status;

    public UserDTO() {
    }

    public UserDTO(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public UserDTO(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public UserDTO(String email, String name, String password, String role, String status) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.roleID = role;
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
