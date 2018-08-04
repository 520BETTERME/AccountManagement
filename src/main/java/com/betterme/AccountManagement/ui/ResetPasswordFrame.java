package com.betterme.AccountManagement.ui;

import com.betterme.AccountManagement.SetUp;
import com.betterme.AccountManagement.utils.ConfigParser;
import com.betterme.AccountManagement.utils.Crypto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.util.Set;

public class ResetPasswordFrame extends FirstLoginFrame {

    public ResetPasswordFrame() {
        init();
    }

    public void init() {

        super.init1();

        TextLabel textLabel = new TextLabel("请先回答下面的安全问题再设置密码");
        textLabel.setBounds(90, 255, 240, 20);

        TextLabel spLabel = new TextLabel(LoginFrame.safetyProblem, SwingConstants.LEFT, 20, Color.CYAN);
        spLabel.setBounds(90, 285, 400, 25);

        bgPanel.addComps(textLabel, spLabel);
        setVisible(true);
    }

    /**
     * 登录
     */
    private void login(){

        String pwd1 = pwdTextFiled1.getPwd();
        String pwd2 = pwdTextFiled2.getPwd();
        boolean isCorrectAnswer = false;
        try {
            isCorrectAnswer = SetUp.configParser.isCorrectInput(answerFiled.getEncryptedText(),
                    ConfigParser.ANSWER_ELEMENT);
        } catch (Exception e1) {
            e1.printStackTrace();
            AttentionPromptBox.prompt("加载安全问题答案失败，错误信息：\n" + e1.getMessage(), this);
        }

        if (pwd1.equals(pwd2) && pwd1.length() > 3 && pwd2.length() > 3 && isCorrectAnswer) {

            boolean isSuccessed = true;
            try {

                SetUp.configParser.resetPassword(SetUp.crypto.encrypt(pwd1));
            } catch (Exception e1) {
                isSuccessed = false;
                e1.printStackTrace();
                AttentionPromptBox.prompt("密码重置失败，错误信息：\n" + e1.getMessage(), this);
            }

            if (isSuccessed) {
                AttentionPromptBox.prompt("密码重置成功！", this);
                this.dispose();
                new LoginFrame();
            }

        }else if (!isCorrectAnswer) {
            AttentionPromptBox.prompt("答案错误！", this);
        }
    }
    @Override
    public void focusGained(FocusEvent e) {

        if (e.getSource() == pwdTextFiled2) {
            loginButton.setEnabled(true);
        }
    }

    /**
     * 只有登录按钮添加该监听器
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        login();
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            login();
        }
    }
}
