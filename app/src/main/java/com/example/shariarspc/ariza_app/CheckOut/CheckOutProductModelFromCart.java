package com.example.shariarspc.ariza_app.CheckOut;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class CheckOutProductModelFromCart implements Parcelable {
    String checkoutProductName,checkoutProductID;
    float priceOfProduct,totalPriceSingleProduct;
    int CheckOutproductquantity;

    public CheckOutProductModelFromCart(String checkoutProductID , String checkoutProductName, float priceOfProduct, float totalPriceSingleProduct, int CheckOutproductquantity) {
        this.checkoutProductName = checkoutProductName;
        this.checkoutProductID = checkoutProductID;
        this.priceOfProduct = priceOfProduct;
        this.totalPriceSingleProduct = totalPriceSingleProduct;
        this.CheckOutproductquantity = CheckOutproductquantity;
    }


    protected CheckOutProductModelFromCart(Parcel in) {
        checkoutProductName = in.readString();
        checkoutProductID = in.readString();
        priceOfProduct = in.readFloat();
        totalPriceSingleProduct = in.readFloat();
        CheckOutproductquantity = in.readInt();
    }

    public static final Creator<CheckOutProductModelFromCart> CREATOR = new Creator<CheckOutProductModelFromCart>() {
        @Override
        public CheckOutProductModelFromCart createFromParcel(Parcel in) {
            return new CheckOutProductModelFromCart(in);
        }

        @Override
        public CheckOutProductModelFromCart[] newArray(int size) {
            return new CheckOutProductModelFromCart[size];
        }
    };

    public String getCheckoutProductName() {
        return checkoutProductName;
    }

    public void setCheckoutProductName(String checkoutProductName) {
        this.checkoutProductName = checkoutProductName;
    }

    public String getCheckoutProductID() {
        return checkoutProductID;
    }

    public void setCheckoutProductID(String checkoutProductID) {
        this.checkoutProductID = checkoutProductID;
    }

    public float getPriceOfProduct() {
        return priceOfProduct;
    }

    public void setPriceOfProduct(float priceOfProduct) {
        this.priceOfProduct = priceOfProduct;
    }

    public float getTotalPriceSingleProduct() {
        return totalPriceSingleProduct;
    }

    public void setTotalPriceSingleProduct(float totalPriceSingleProduct) {
        this.totalPriceSingleProduct = totalPriceSingleProduct;
    }

    public int getCheckOutproductquantity() {
        return CheckOutproductquantity;
    }

    public void setCheckOutproductquantity(int checkOutproductquantity) {
        CheckOutproductquantity = checkOutproductquantity;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(checkoutProductName);
        dest.writeString(checkoutProductID);
        dest.writeFloat(priceOfProduct);
        dest.writeFloat(totalPriceSingleProduct);
        dest.writeInt(CheckOutproductquantity);
    }
}
