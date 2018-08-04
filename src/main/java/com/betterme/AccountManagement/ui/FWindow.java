package com.betterme.AccountManagement.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FWindow extends JFrame{

    private final String APP_NAME = "AccountManagement";
    private final Image APP_ICON = new ImageIcon("sources/images/icon.png").getImage();
    static final int WIDTH = 960;
    static final int HEIGHT = 632;

    public FWindow() {
        setTitle(APP_NAME);
        setIconImage(APP_ICON);
        setSize(WIDTH, HEIGHT);
        setResizable(false);    //窗体不可以自由改变大小
        setLocationRelativeTo(null);    //默认居中显示
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setVisible(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //super.windowClosing(e);
                JLabel sureText = new JLabel("确认退出？");
                sureText.setForeground(Color.RED);
                sureText.setFont(new Font("黑体", Font.PLAIN, 16));
                int value = JOptionPane.showConfirmDialog(FWindow.this, sureText, "请确认",
                        JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

                if (value == JOptionPane.YES_OPTION){
                    System.exit(0);
                }else {
                    return;
                }
            }
        });
    }

}
