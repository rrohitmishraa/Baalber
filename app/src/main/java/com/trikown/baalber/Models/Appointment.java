package com.trikown.baalber.Models;

import java.util.ArrayList;

public class Appointment {
    String Complete, ShopCode, Time, UserCode;
    ArrayList<String> Requirements;

    public Appointment() {
    }

    public Appointment(String complete, String shopCode, String time, String userCode, ArrayList<String> requirements) {
        Complete = complete;
        ShopCode = shopCode;
        Time = time;
        UserCode = userCode;
        Requirements = requirements;
    }

    public String getComplete() {
        return Complete;
    }

    public void setComplete(String complete) {
        Complete = complete;
    }

    public String getShopCode() {
        return ShopCode;
    }

    public void setShopCode(String shopCode) {
        ShopCode = shopCode;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getUserCode() {
        return UserCode;
    }

    public void setUserCode(String userCode) {
        UserCode = userCode;
    }

    public ArrayList<String> getRequirements() {
        return Requirements;
    }

    public void setRequirements(ArrayList<String> requirements) {
        Requirements = requirements;
    }
}
