package com.trikown.baalber.Models;

public class Customer {
    String Email, ProfilePic, UserName;

    public Customer() {
    }

    public Customer(String email, String profilePic, String userName) {
        Email = email;
        ProfilePic = profilePic;
        UserName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getProfilePic() {
        return ProfilePic;
    }

    public void setProfilePic(String profilePic) {
        ProfilePic = profilePic;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }
}
