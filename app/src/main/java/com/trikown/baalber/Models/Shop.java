package com.trikown.baalber.Models;

public class Shop {
    private String Address, ShopName, PinCode, Rating, ShopCode, Open, Close;

    public Shop() {
    }

    public Shop(String address, String shopName, String pinCode, String rating, String shopCode, String open, String close) {
        Address = address;
        ShopName = shopName;
        PinCode = pinCode;
        Rating = rating;
        ShopCode = shopCode;
        Open = open;
        Close = close;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String shopName) {
        ShopName = shopName;
    }

    public String getPinCode() {
        return PinCode;
    }

    public void setPinCode(String pinCode) {
        PinCode = pinCode;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public String getShopCode() {
        return ShopCode;
    }

    public void setShopCode(String shopCode) {
        ShopCode = shopCode;
    }

    public String getOpen() {
        return Open;
    }

    public void setOpen(String open) {
        Open = open;
    }

    public String getClose() {
        return Close;
    }

    public void setClose(String close) {
        Close = close;
    }
}