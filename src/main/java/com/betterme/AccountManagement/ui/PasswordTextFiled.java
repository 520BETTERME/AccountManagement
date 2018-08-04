package com.betterme.AccountManagement.ui;

import com.betterme.AccountManagement.SetUp;

import javax.swing.*;
import java.awt.*;

public class PasswordTextFiled extends JPasswordField{

    private String pwd;

    public PasswordTextFiled(){

        this.setBackground(new Color(232,232,232));
        this.setForeground(Color.BLACK);
        this.setFont(new Font("黑体", Font.PLAIN, 17));
        this.setBorder(null);
    }

    public PasswordTextFiled(Color bgColor, Color fontColor, int fontSize) {

        this.setBackground(bgColor);
        this.setForeground(fontColor);
        this.setFont(new Font("黑体", Font.PLAIN, fontSize));
        this.setBorder(null);
    }

    public String getPwd() {

        pwd = String.valueOf(getPassword());
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }


    /**
     * 得到加密后的密码
     * @return
     * @throws Exception
     */
    public String getEncryptedPwd() throws Exception {

        return SetUp.crypto.encrypt(getPwd());
    }
}
