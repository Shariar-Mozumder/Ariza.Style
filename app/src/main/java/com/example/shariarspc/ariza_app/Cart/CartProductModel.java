package com.example.shariarspc.ariza_app.Cart;

public class CartProductModel {
    String nameCart,idCart,priceCart,imageCart;
    int quantityy;

    public CartProductModel() {
    }

    public CartProductModel(String idCart, String nameCart , String priceCart, String imageCart,int quantityy) {
        this.nameCart = nameCart;
        this.idCart = idCart;
        this.priceCart = priceCart;
        this.imageCart = imageCart;
        this.quantityy=quantityy;
    }

    public int getQuantityy() {
        return quantityy;
    }

    public void setQuantityy(int quantityy) {
        this.quantityy = quantityy;
    }

    public String getNameCart() {
        return nameCart;
    }

    public void setNameCart(String nameCart) {
        this.nameCart = nameCart;
    }

    public String getIdCart() {
        return idCart;
    }

    public void setIdCart(String idCart) {
        this.idCart = idCart;
    }

    public String getPriceCart() {
        return priceCart;
    }

    public void setPriceCart(String priceCart) {
        this.priceCart = priceCart;
    }

    public String getImageCart() {
        return imageCart;
    }

    public void setImageCart(String imageCart) {
        this.imageCart = imageCart;
    }
}
