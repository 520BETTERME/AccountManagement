package com.betterme.AccountManagement.ui;

import javax.swing.*;
import java.awt.*;

public class FButton extends JButton {

    public FButton (){
        this.setFocusPainted(false);    //除去焦点的框
        this.setBorderPainted(false);   //不打印边框
    }
    /**
     * @param text 文本内容
     * @param bgColor 背景色
     * @param fontColor 字体色
     * @param fontSize 字体大小
     */
    public FButton(String text, Color bgColor, Color fontColor, int fontSize) {
        super(text);
        this.setBackground(bgColor);
        this.setForeground(fontColor);
        this.setFont(new Font("黑体", Font.PLAIN,fontSize));
        this.setFocusPainted(false);
        this.setBorder(null);
    }

    /**
     * 设置背景图符合按钮的大小
     */
    public void setBgImage(String iconPath){

        ImageIcon imageIcon = new ImageIcon(iconPath);
        Image image = imageIcon.getImage().getScaledInstance(getWidth(), getHeight(),
                imageIcon.getImage().SCALE_DEFAULT);

        imageIcon = new ImageIcon(image);
        setIcon(imageIcon);
    }

    /**
     * 设置按钮背景透明
     */
    public void setBgTransparency(){

//        setContentAreaFilled(false);  //除去默认的背景填充
        this.setBackground(Color.BLACK);
        this.setOpaque(false);   //设置背景透明
//        this.setMargin(new Insets(0, 0, 0, 0));   //将边框外的上下左右空间设置为0
//        this.setIconTextGap(0);   //将标签中显示的文本和图标之间的间隔量设置为0
//        this.setBorderPainted(false);   //不打印边框

    }
}
