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
public class DrinkErrorDTO {
    private String nameErr;
    private String imageErr;
    private String descriptionErr;
    private String priceErr;
    private String categoryErr;
    private String quantityErr;
    private String statusErr;

    public DrinkErrorDTO() {
    }

    public DrinkErrorDTO(String nameErr, String imageErr, String descriptionErr, String priceErr, String categoryErr, String quantityErr, String statusErr) {
        this.nameErr = nameErr;
        this.imageErr = imageErr;
        this.descriptionErr = descriptionErr;
        this.priceErr = priceErr;
        this.categoryErr = categoryErr;
        this.quantityErr = quantityErr;
        this.statusErr = statusErr;
    }

    public String getNameErr() {
        return nameErr;
    }

    public void setNameErr(String nameErr) {
        this.nameErr = nameErr;
    }

    public String getImageErr() {
        return imageErr;
    }

    public void setImageErr(String imageErr) {
        this.imageErr = imageErr;
    }

    public String getDescriptionErr() {
        return descriptionErr;
    }

    public void setDescriptionErr(String descriptionErr) {
        this.descriptionErr = descriptionErr;
    }

    public String getPriceErr() {
        return priceErr;
    }

    public void setPriceErr(String priceErr) {
        this.priceErr = priceErr;
    }

    public String getCategoryErr() {
        return categoryErr;
    }

    public void setCategoryErr(String categoryErr) {
        this.categoryErr = categoryErr;
    }

    public String getQuantityErr() {
        return quantityErr;
    }

    public void setQuantityErr(String quantityErr) {
        this.quantityErr = quantityErr;
    }

    public String getStatusErr() {
        return statusErr;
    }

    public void setStatusErr(String statusErr) {
        this.statusErr = statusErr;
    }
    

    
}
