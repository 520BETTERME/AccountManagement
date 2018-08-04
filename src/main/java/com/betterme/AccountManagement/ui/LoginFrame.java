package com.betterme.AccountManagement.ui;

import com.betterme.AccountManagement.SetUp;
import com.betterme.AccountManagement.utils.ConfigParser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LoginFrame extends FirstLoginFrame implements ActionListener, KeyListener{

    private PasswordTextFiled userPwdFiled;
    private FButton loginButton;
    private FButton forgetPwdButton;
    public static String safetyProblem;

    public LoginFrame() {
        super();
        init();
    }

    public void init(){

        TextLabel textLabel = new TextLabel("输入密码");
        textLabel.setBounds(120, 192, 70,20);

        userPwdFiled = new PasswordTextFiled();
        userPwdFiled.setBounds(120, 220, 200, 30);
        userPwdFiled.addKeyListener(this);

        forgetPwdButton = new FButton("忘记密码", new Color(0,179,254), Color.BLACK, 15);
        forgetPwdButton.setBounds(120, 270, 70, 25);
        forgetPwdButton.addActionListener(this);

        loginButton = new FButton("登录", new Color(0, 179, 254), Color.BLACK, 15);
        loginButton.setBounds(270, 270, 50, 25);
        loginButton.addActionListener(this);
        

        bgPanel.addComps(textLabel, userPwdFiled, forgetPwdButton, loginButton);
        setVisible(true);
    }

    /**
     * 登录
     */
    private void login(){

        try {
            if (SetUp.configParser.isCorrectInput(userPwdFiled.getEncryptedPwd(), ConfigParser.PASSWORD_ELEMENT)) {
                new AccountInfoFrame();
                this.dispose();
            }else {
                AttentionPromptBox.prompt("密码错误！\n请确认后输入", this);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            AttentionPromptBox.prompt("载入失败，错误信息：\n" + e1.getMessage(), this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        /**
         * 登录按钮事件监听处理
         */
        if (e.getSource() == loginButton) {
            login();
            /**
             * 忘记密码事件监听处理
             */
        } else if (e.getSource() == forgetPwdButton) {
            boolean isError = false;
            try {
                safetyProblem = SetUp.crypto.decrypt(SetUp.configParser.getSafeProblem());
            } catch (Exception e2) {
                isError = true;
                e2.printStackTrace();
                AttentionPromptBox.prompt("读取安全问题失败，错误信息：\n" + e2.getMessage(), this);
            }
            if (!isError) {
                new ResetPasswordFrame();
                this.dispose();
            }

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            login();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
