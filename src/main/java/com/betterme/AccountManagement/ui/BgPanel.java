package com.betterme.AccountManagement.ui;

import javax.swing.*;
import java.awt.*;

/**
 * 实现Runnable接口用于判断设置窗口中一些按妞是否可用
 */
public class BgPanel extends JPanel{
    private Image bgImg;

    public BgPanel(boolean addBgImage) {

        if (addBgImage) {
            bgImg = new ImageIcon("sources/images/bg.jpg").getImage();
        } else {
            this.setBackground(new Color(186, 225, 234));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImg, 0, 0, FWindow.WIDTH, FWindow.HEIGHT, this);
    }

    /**
     * 批量添加组件
     * @param comp 可变参数
     */
    public void addComps(Component...comp) {

        for (int i = 0; i < comp.length; i++ ){
            add(comp[i]);
        }
    }

}
