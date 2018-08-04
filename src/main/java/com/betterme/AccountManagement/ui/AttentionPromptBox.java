package com.betterme.AccountManagement.ui;

import javax.swing.*;
import java.awt.*;

public class AttentionPromptBox {

    public static void prompt(String message, Component comp){

        JLabel sureText = new JLabel(message);
        sureText.setForeground(Color.RED);
        sureText.setFont(new Font("黑体", Font.PLAIN, 16));
//        int value = JOptionPane.showConfirmDialog(comp, sureText, title,
//                JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
//
//        if (value == JOptionPane.YES_OPTION){
//            System.exit(0);
//        }else {
//            return;
//        }
        JOptionPane.showMessageDialog(comp, sureText);
    }
}
