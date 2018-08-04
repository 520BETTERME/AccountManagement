package com.betterme.AccountManagement;

public class AccountInfo {

    private String platform;
    private String userName;
    private String password;
    private String email;
    private String website;
    private String telephone;

    public AccountInfo(String plarform, String userName, String password, String email, String website, String telephone) {
        this.platform = plarform;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.website = website;
        this.telephone = telephone;
    }

    public AccountInfo() {
    }

    public String getPlarform() {
        return platform;
    }

    public void setPlarform(String plarform) {
        this.platform = plarform;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
