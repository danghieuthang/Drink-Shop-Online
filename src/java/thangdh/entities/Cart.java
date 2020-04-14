/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thangdh.entities;


import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author dhtha
 */
public class Cart extends HashMap implements Serializable{

    public Cart() {
        super();
    }

    public void addDrink(CartItem drink) {
        int key = drink.getDrink().getID();
        if (this.containsKey(key)) {
            int oldQuantity = ((CartItem) this.get(key)).getQuantity();
            ((CartItem) this.get(key)).setQuantity(oldQuantity + 1);
        } else {
            this.put(key, drink);
        }
    }

    public void addDrinkWithQuantity(CartItem cartItemDTO) {
        int key = cartItemDTO.getDrink().getID();
        if (this.containsKey(key)) {
            int oldQuantity = ((CartItem) this.get(key)).getQuantity();
            ((CartItem) this.get(key)).setQuantity(oldQuantity + cartItemDTO.getQuantity());
        } else {
            this.put(key, cartItemDTO);
        }
    }

    public boolean updateDrink(int key, int quantity) {

        if (this.containsKey(key)) {
            ((CartItem) this.get(key)).setQuantity(quantity);
            return true;
        }
        return false;
    }

    public boolean removeDrink(int id) {
        if (this.containsKey(id)) {
            this.remove(id);
            return true;
        }

        return false;
    }

    public float getTotalPrice() {
        float total = 0;
        for (Object itemDTO : this.values()) {
            CartItem drink = (CartItem) itemDTO;
            total += (drink.getDrink().getPrice()) * drink.getQuantity();
        }
        return total;
    }

}
