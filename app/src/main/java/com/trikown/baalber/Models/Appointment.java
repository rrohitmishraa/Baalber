package com.trikown.baalber.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Appointment implements Parcelable {
    String Complete, ShopCode, Time, UserCode, Date;
    int Haircut, Shave;

    public Appointment(String complete, String shopCode, String time, String userCode, String date, int haircut, int shave) {
        Complete = complete;
        ShopCode = shopCode;
        Time = time;
        UserCode = userCode;
        Date = date;
        Haircut = haircut;
        Shave = shave;
    }

    protected Appointment(Parcel in) {
        Complete = in.readString();
        ShopCode = in.readString();
        Time = in.readString();
        UserCode = in.readString();
        Date = in.readString();
        Haircut = in.readInt();
        Shave = in.readInt();
    }

    public static final Creator<Appointment> CREATOR = new Creator<Appointment>() {
        @Override
        public Appointment createFromParcel(Parcel in) {
            return new Appointment(in);
        }

        @Override
        public Appointment[] newArray(int size) {
            return new Appointment[size];
        }
    };

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

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getHaircut() {
        return Haircut;
    }

    public void setHaircut(int haircut) {
        Haircut = haircut;
    }

    public int getShave() {
        return Shave;
    }

    public void setShave(int shave) {
        Shave = shave;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Complete);
        parcel.writeString(ShopCode);
        parcel.writeString(Time);
        parcel.writeString(UserCode);
        parcel.writeString(Date);
        parcel.writeInt(Haircut);
        parcel.writeInt(Shave);
    }
}
