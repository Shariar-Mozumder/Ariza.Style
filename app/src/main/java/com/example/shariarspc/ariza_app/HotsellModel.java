package com.example.shariarspc.ariza_app;

public class HotsellModel {
    int image;
    String product_name;

    public HotsellModel(int image, String product_name) {
        this.image = image;
        this.product_name = product_name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }
}
