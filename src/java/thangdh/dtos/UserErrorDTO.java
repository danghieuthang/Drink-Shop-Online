/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thangdh.dtos;

/**
 *
 * @author dhtha
 */
public class UserErrorDTO {

    private String emailErr;
    private String passwordErr;

    public UserErrorDTO() {
    }

    public UserErrorDTO(String emailErr, String passwordErr) {
        this.emailErr = emailErr;
        this.passwordErr = passwordErr;
    }

    public String getEmailErr() {
        return emailErr;
    }

    public void setEmailErr(String emailErr) {
        this.emailErr = emailErr;
    }

    public String getPasswordErr() {
        return passwordErr;
    }

    public void setPasswordErr(String passwordErr) {
        this.passwordErr = passwordErr;
    }

}
