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
public class DrinkDTO {

    private int ID;
    private String name;
    private String image;
    private String description;
    private float price;
    private Date createDate;
    private CategoryDTO category;
    private int quantity;
    private String status;

    public DrinkDTO() {
    }

    public DrinkDTO(String name, String image, String description, float price, Date createDate, CategoryDTO category, int quantity, String status) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.createDate = createDate;
        this.category = category;
        this.quantity = quantity;
        this.status = status;
    }

    public DrinkDTO(int ID, String name, String image, String description, float price, Date createDate, CategoryDTO category, int quantity, String status) {
        this.ID = ID;
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.createDate = createDate;
        this.category = category;
        this.quantity = quantity;
        this.status = status;
    }

    public DrinkDTO(int ID, String name, String image, String description, float price, Date createDate, int quantity, String status) {
        this.ID = ID;
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.createDate = createDate;
        this.quantity = quantity;
        this.status = status;
    }

   
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
