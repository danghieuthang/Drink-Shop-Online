/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thangdh.dtos;

import java.sql.Date;

/**
 *
 * @author dhtha
 */
public class TransactionDTO {
    private int ID;
    private UserDTO user;
    private Date createDate;
    private float Amount;

    public TransactionDTO() {
    }

    public TransactionDTO(UserDTO user, Date createDate, float Amount) {
        this.user = user;
        this.createDate = createDate;
        this.Amount = Amount;
    }

    public TransactionDTO(int ID, Date createDate, float Amount) {
        this.ID = ID;
        this.createDate = createDate;
        this.Amount = Amount;
    }

    public TransactionDTO(int ID, UserDTO user, Date createDate, float Amount) {
        this.ID = ID;
        this.user = user;
        this.createDate = createDate;
        this.Amount = Amount;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public float getAmount() {
        return Amount;
    }

    public void setAmount(float Amount) {
        this.Amount = Amount;
    }
    
    
}
