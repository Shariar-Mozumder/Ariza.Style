package com.example.shariarspc.ariza_app.HotSell;

public class HotsellModel {
    String image;
    String product_name;
    String price;
    String productID;
    String[] images;
    String metadescription;
    String stockstatus;
    String quantity;
    String description;
    String weight;
    String length;
    String width;
    String height;


    public HotsellModel(String image, String product_name, String price, String productID, String[] images, String metadescription, String stockstatus, String quantity, String description, String weight, String length, String width, String height) {
        this.image = image;
        this.product_name = product_name;
        this.price = price;
        this.productID = productID;
        this.images = images;
        this.metadescription = metadescription;
        this.stockstatus = stockstatus;
        this.quantity = quantity;
        this.description = description;
        this.weight = weight;
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public String getMetadescription() {
        return metadescription;
    }

    public void setMetadescription(String metadescription) {
        this.metadescription = metadescription;
    }

    public String getStockstatus() {
        return stockstatus;
    }

    public void setStockstatus(String stockstatus) {
        this.stockstatus = stockstatus;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }
}
