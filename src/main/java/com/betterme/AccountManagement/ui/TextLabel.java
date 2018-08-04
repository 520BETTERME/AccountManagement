package com.betterme.AccountManagement.ui;

import javax.swing.*;
import java.awt.*;

public class TextLabel extends JLabel{

    public TextLabel(String text) {

        super(text);
        this.setHorizontalAlignment(LEFT);
        this.setFont(new Font("黑体", Font.PLAIN,15));
        this.setForeground(Color.BLACK);
    }

    /**
     * @param text 文本标签的内容
     * @param horizontalAlignment 文本对齐方式
     * @param fontSize 字体大小
     * @param fontColor 字体颜色
     */
    public TextLabel(String text, int horizontalAlignment, int fontSize, Color fontColor) {
        super(text, horizontalAlignment);
        this.setFont(new Font("黑体", Font.PLAIN,fontSize));
        this.setForeground(fontColor);
    }

}
