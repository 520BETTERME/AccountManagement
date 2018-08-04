package com.betterme.AccountManagement;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test {
    public static void main(String[] args) throws Exception {
        JFrame f=new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel p=new JPanel();
        f.add(p);

        JButton tb=new JButton();

        ImageIcon icon=new ImageIcon(new URL("http://ico.ooopic.com/iconset02/2/gif/62717.gif"));
        icon=new ImageIcon(icon.getImage().getScaledInstance(100, 32, Image.SCALE_SMOOTH));

        tb.setIcon(icon);
        p.add(tb);

        f.setSize(400,300);
        f.setVisible(true);
    }
}