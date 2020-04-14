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
public class OrderDTO {
    private int ID;
    private TransactionDTO transaction;
    private DrinkDTO drink;
   
    private int quantity;
    private float Amount;
    public OrderDTO() {
    }

    public OrderDTO(TransactionDTO transaction, DrinkDTO drink, int quantity, float Amount) {
        this.transaction = transaction;
        this.drink = drink;
        this.quantity = quantity;
        this.Amount = Amount;
    }

    public OrderDTO(int ID, DrinkDTO drink, int quantity, float Amount) {
        this.ID = ID;
        this.drink = drink;
        this.quantity = quantity;
        this.Amount = Amount;
    }

    public OrderDTO(int ID, TransactionDTO transaction, DrinkDTO drink, int quantity, float Amount) {
        this.ID = ID;
        this.transaction = transaction;
        this.drink = drink;
        this.quantity = quantity;
        this.Amount = Amount;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public TransactionDTO getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionDTO transaction) {
        this.transaction = transaction;
    }

    public DrinkDTO getDrink() {
        return drink;
    }

    public void setDrink(DrinkDTO drink) {
        this.drink = drink;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getAmount() {
        return Amount;
    }

    public void setAmount(float Amount) {
        this.Amount = Amount;
    }
    
    
            
}
