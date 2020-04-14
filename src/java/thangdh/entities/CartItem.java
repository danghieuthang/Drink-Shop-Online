/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thangdh.entities;

import thangdh.dtos.DrinkDTO;


/**
 *
 * @author dhtha
 */
public class CartItem{

    private DrinkDTO drink;
    private int quantity;

    public CartItem() {
    }

    public CartItem(DrinkDTO drink, int quantity) {
        this.drink = drink;
        this.quantity = quantity;
    }
    

    public CartItem(DrinkDTO drink) {
        this.drink = drink;
        this.quantity = 1;
    }

    public float getTotalPrice() {
        return quantity * drink.getPrice();
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

}
