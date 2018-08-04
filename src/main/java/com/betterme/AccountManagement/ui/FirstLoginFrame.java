package com.betterme.AccountManagement.ui;

import com.betterme.AccountManagement.SetUp;
import com.betterme.AccountManagement.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FirstLoginFrame extends FWindow implements FocusListener, ActionListener, KeyListener{

    protected PasswordTextFiled pwdTextFiled1;
    protected PasswordTextFiled pwdTextFiled2;
    private InputTextFiled spFiled;
    protected InputTextFiled answerFiled;
    protected FButton loginButton;
    protected BgPanel bgPanel;



    public FirstLoginFrame() {

        this.bgPanel = new BgPanel(true);
        bgPanel.setLayout(null);
        FButton aboutButton = new FButton();
        aboutButton.setBounds(915, 20, 25, 25);
        aboutButton.setBgTransparency();
        aboutButton.setBgImage("sources/images/about.png");
        aboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new FirstLoginFrame.AboutDialog();
            }
        });
        bgPanel.add(aboutButton);
        this.add(bgPanel);

    }

    public void init1(){

        String firstUseTip1 = "第一次使用，请设置登录密码";
        String firstUseTip2 = "再次输入密码，确认是否相同";
        String firstUseTip4 = "请输入安全问题的答案";

        TextLabel tipLabel1 = new TextLabel(firstUseTip1);
        tipLabel1.setBounds(120, 125, 200, 20);

        pwdTextFiled1 = new PasswordTextFiled();
        pwdTextFiled1.setBounds(120, 150, 250, 25);
        pwdTextFiled1.addFocusListener(this);

        TextLabel tipLabel2 = new TextLabel(firstUseTip2);
        tipLabel2.setBounds(120, 185, 200, 20);

        pwdTextFiled2 = new PasswordTextFiled();
        pwdTextFiled2.setBounds(120, 210, 250, 25);
        pwdTextFiled2.setEditable(false);
        pwdTextFiled2.addFocusListener(this);
        pwdTextFiled2.addKeyListener(this);

        TextLabel tipLabel4 = new TextLabel(firstUseTip4);
        tipLabel4.setBounds(90, 320, 200, 20);

        answerFiled = new InputTextFiled();
        answerFiled.setBounds(90, 345, 310, 25);
        answerFiled.addFocusListener(this);
        answerFiled.addKeyListener(this);

        loginButton = new FButton("登录", new Color(0,179,254), Color.BLACK, 18);
        loginButton.setBounds(200, 400, 70, 30);
        loginButton.setEnabled(false);
        loginButton.addActionListener(this);

        bgPanel.addComps(tipLabel1, pwdTextFiled1, tipLabel2, pwdTextFiled2, tipLabel4, answerFiled, loginButton);

    }

    public void init2() {

        init1();
        String firstUseTip3 = "请输入安全问题用于找回密码";
        //String firstUseTip4 = "请输入安全问题的答案";
        TextLabel tipLabel3 = new TextLabel(firstUseTip3);
        tipLabel3.setBounds(90, 255, 200, 20);

        spFiled = new InputTextFiled();
        spFiled.setBounds(90, 285, 310, 25);
        spFiled.addFocusListener(this);

        bgPanel.addComps( tipLabel3,  spFiled);
        setVisible(true);

    }

    /**
     * 登录
     */
    private void login() {

        String password1 = pwdTextFiled1.getPwd();
        String password2 = pwdTextFiled2.getPwd();
        String safeProblem = spFiled.getText();
        String answer = answerFiled.getText();

        if (password1.equals(password2) && password1.length() > 0
                && safeProblem.length() >= 4 && answer.length() > 0) {

            boolean isError = false;
            try {
                //User user = SetUp.crypto.encryptUser(new User(password1,safeProblem, safeProblem));
                User user = new User(pwdTextFiled1.getEncryptedPwd(), spFiled.getEncryptedText(), answerFiled.getEncryptedText());
                SetUp.configParser.addUserElements(user);
            } catch (Exception e1) {
                e1.printStackTrace();
                isError = true;
                AttentionPromptBox.prompt("加密信息时出行错误\n请重启程序尝试修复,错误：\n" + e1.getMessage(), this);
            }
            if (!isError){
                AttentionPromptBox.prompt("请手动重启程序使设置生效", this);
                System.exit(0);
//                new AccountInfoFrame().init();
//                this.dispose();
            }
        }else if (safeProblem.length() < 4) {
            AttentionPromptBox.prompt("输入的问题不配做安全问题！",this);
        }else if (answer.length() < 1){
            AttentionPromptBox.prompt("请自行回答安全问题！",this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /**
         * 登录按钮监听事件
         */
        login();
    }

    @Override
    public void focusGained(FocusEvent e) {

        String pwd1 = pwdTextFiled1.getPwd();
        String pwd2 = pwdTextFiled2.getPwd();

        if (e.getSource() == answerFiled) {
            if (pwd1.length() > 2 && pwd2.length() > 2 && pwd1.equals(pwd2)){
                loginButton.setEnabled(true);
            }
        }
    }

    @Override
    public void focusLost(FocusEvent e) {

        String pwd1 = pwdTextFiled1.getPwd();
        String pwd2 = pwdTextFiled2.getPwd();

        if (e.getSource() == pwdTextFiled1) {
            if (pwd1.length() <= 2) {
                AttentionPromptBox.prompt("密码太短或者没有输入密码", this);
            } else if (pwd2.length() != 0 && !pwd1.equals(pwd2)) {
                AttentionPromptBox.prompt("两次输入的密码不一致\n请重新输入！", this);
            }else {
                pwdTextFiled2.setEditable(true);
            }
        }else if (e.getSource() == pwdTextFiled2) {
            if (!pwd2.equals(pwd1)){
                if (pwdTextFiled1.getPwd().length() != 0) {
                    AttentionPromptBox.prompt("两次输入的密码不一致\n请重新输入！", this);
                }
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

    /**
     * 关于对话框
     */
    private class AboutDialog extends JDialog{

        public AboutDialog() {

            this.setTitle("关于");
            Image icon = new ImageIcon("sources/images/icon.png").getImage();
            this.setIconImage(icon);
            this.setSize(840, 400);
            this.setResizable(false);
            this.setModal(true);
            this.setLocationByPlatform(true);
            JTextArea aboutArea = new JTextArea();
            JScrollPane jScrollPane = new JScrollPane(aboutArea);
            jScrollPane.setBorder(null);
            //jScrollPane.setBackground(new Color(186, 225, 234));
            aboutArea.setFont(new Font("黑体", Font.PLAIN, 19));
            aboutArea.setEditable(false);
            aboutArea.setBorder(null);
            //aboutArea.setBounds(50, 0, 500, 400);
            aboutArea.setBackground(new Color(186, 225, 234));
            try{
                FileReader fr = new FileReader("sources/about");
                BufferedReader br = new BufferedReader(fr);
                String aboutStr = null;
                while ((aboutStr = br.readLine()) != null){
                    //aboutArea.append(aboutStr + "\n");
                    //解决乱码问题
                    aboutArea.append(new String(aboutStr.getBytes(), "UTF-8") + "\n");
                }
                br.close();
                fr.close();
            }catch (IOException e){
                aboutArea.setText(e.getMessage());
                //e.printStackTrace();
            }
            this.add(jScrollPane);
            this.setVisible(true);
        }
    }

}
