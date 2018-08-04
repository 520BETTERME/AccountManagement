package com.betterme.AccountManagement.ui;

import com.betterme.AccountManagement.SetUp;

import javax.swing.*;
import java.awt.*;


public class InputTextFiled extends JTextField {


    public InputTextFiled() {

        this.setBackground(new Color(232,232,232));
        this.setForeground(Color.BLACK);
        this.setFont(new Font("黑体", Font.PLAIN, 17));
        this.setBorder(null);
    }

    /**
     *
     * @param bgColor 背景色
     * @param fontColor 字体色
     * @param fontSize 字体大小
     */
    public InputTextFiled(Color bgColor, Color fontColor, int fontSize) {

        this.setBackground(bgColor);
        this.setForeground(fontColor);
        this.setFont(new Font("黑体", Font.PLAIN, fontSize));
        this.setBorder(null);

    }

    /**
     * 得到加密后输入框中的字符串
     * @return
     * @throws Exception
     */
    public String getEncryptedText() throws Exception {

        return SetUp.crypto.encrypt(getText());
    }

}
