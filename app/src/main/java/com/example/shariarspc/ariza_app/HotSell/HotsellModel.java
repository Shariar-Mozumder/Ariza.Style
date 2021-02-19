package com.example.shariarspc.ariza_app.HotSell;

import java.util.HashMap;

public class HotsellModel {
    String imagehot;
    String product_namehot;
    String pricehot;
    String productIDhot;
    String[] imageshot;
    String metadescriptionhot;
    String stockstatushot;
    String quantityhot;
    String descriptionhot;
    String weighthot;
    String lengthhot;
    String widthhot;
    String heighthot;
    String modelhot;
    String sizehot[];
    HashMap optionMap;


    public HotsellModel(String imagehot, String product_namehot, String pricehot, String productIDhot, String[] imageshot, String metadescriptionhot, String stockstatushot, String quantityhot, String descriptionhot, String weighthot, String lengthhot, String widthhot, String heighthot, String modelhot, String[] sizehot, HashMap optionMap) {
        this.imagehot = imagehot;
        this.product_namehot = product_namehot;
        this.pricehot = pricehot;
        this.productIDhot = productIDhot;
        this.imageshot = imageshot;
        this.metadescriptionhot = metadescriptionhot;
        this.stockstatushot = stockstatushot;
        this.quantityhot = quantityhot;
        this.descriptionhot = descriptionhot;
        this.weighthot = weighthot;
        this.lengthhot = lengthhot;
        this.widthhot = widthhot;
        this.heighthot = heighthot;
        this.modelhot=modelhot;
        this.sizehot=sizehot;
        this.optionMap=optionMap;
    }

    public HashMap getOptionMap() {
        return optionMap;
    }

    public void setOptionMap(HashMap optionMap) {
        this.optionMap = optionMap;
    }

    public String getModelhot() {
        return modelhot;
    }

    public void setModelhot(String modelhot) {
        this.modelhot = modelhot;
    }

    public String[] getSizehot() {
        return sizehot;
    }

    public void setSizehot(String[] sizehot) {
        this.sizehot = sizehot;
    }

    public String getImagehot() {
        return imagehot;
    }

    public void setImagehot(String imagehot) {
        this.imagehot = imagehot;
    }

    public String getProduct_namehot() {
        return product_namehot;
    }

    public void setProduct_namehot(String product_namehot) {
        this.product_namehot = product_namehot;
    }

    public String getPricehot() {
        return pricehot;
    }

    public void setPricehot(String pricehot) {
        this.pricehot = pricehot;
    }

    public String getProductIDhot() {
        return productIDhot;
    }

    public void setProductIDhot(String productIDhot) {
        this.productIDhot = productIDhot;
    }

    public String[] getImageshot() {
        return imageshot;
    }

    public void setImageshot(String[] imageshot) {
        this.imageshot = imageshot;
    }

    public String getMetadescriptionhot() {
        return metadescriptionhot;
    }

    public void setMetadescriptionhot(String metadescriptionhot) {
        this.metadescriptionhot = metadescriptionhot;
    }

    public String getStockstatushot() {
        return stockstatushot;
    }

    public void setStockstatushot(String stockstatushot) {
        this.stockstatushot = stockstatushot;
    }

    public String getQuantityhot() {
        return quantityhot;
    }

    public void setQuantityhot(String quantityhot) {
        this.quantityhot = quantityhot;
    }

    public String getDescriptionhot() {
        return descriptionhot;
    }

    public void setDescriptionhot(String descriptionhot) {
        this.descriptionhot = descriptionhot;
    }

    public String getWeighthot() {
        return weighthot;
    }

    public void setWeighthot(String weighthot) {
        this.weighthot = weighthot;
    }

    public String getLengthhot() {
        return lengthhot;
    }

    public void setLengthhot(String lengthhot) {
        this.lengthhot = lengthhot;
    }

    public String getWidthhot() {
        return widthhot;
    }

    public void setWidthhot(String widthhot) {
        this.widthhot = widthhot;
    }

    public String getHeighthot() {
        return heighthot;
    }

    public void setHeighthot(String heighthot) {
        this.heighthot = heighthot;
    }
}
