package com.betterme.AccountManagement.ui;

import com.betterme.AccountManagement.AccountInfo;
import com.betterme.AccountManagement.SetUp;
import com.betterme.AccountManagement.utils.ConfigParser;
import com.betterme.AccountManagement.utils.Crypto;
import org.dom4j.DocumentException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.List;

public class AccountInfoFrame extends FWindow implements FocusListener, ActionListener, MouseListener, KeyListener{

    private InputTextFiled searchTextFiled;
    private FButton searchButton;
    private InputTextFiled platformTF;
    private InputTextFiled userNameTF;
    private InputTextFiled pwdTF;
    private InputTextFiled emailTF;
    private InputTextFiled webTF;
    private InputTextFiled teleTF;
    private FButton cancleButton;
    private FButton saveButton;
    private FButton logoutButton;
    private DisplayArea displayArea;

    private Crypto crypto;
    private ConfigParser configParser;


    public AccountInfoFrame() {
        init();
    }

    public void init(){

        crypto = new Crypto();
        configParser = new ConfigParser();
        BgPanel bgPanel = new BgPanel(false);
        add(bgPanel);
        bgPanel.setLayout(null);

        searchTextFiled = new InputTextFiled();
        searchTextFiled.setBounds(40, 40, 330, 30);
        searchTextFiled.addFocusListener(this);
        searchTextFiled.addKeyListener(this);

        searchButton = new FButton();
        searchButton.setBounds(370, 40, 30, 30);
        searchButton.setBackground(new Color(232, 232, 232));
        searchButton.setBgImage("sources/images/search.png");
        searchButton.addMouseListener(this);
        searchButton.addActionListener(this);

        TextLabel tipLabel = new TextLabel("输入账户信息：",SwingConstants.LEFT,18, Color.BLACK);
        tipLabel.setBounds(40, 85, 130, 25);

        TextLabel platformLabel = new TextLabel("注册平台：",SwingConstants.RIGHT,17,Color.BLACK);
        platformLabel.setBounds(40, 120, 85, 30);

        platformTF = new InputTextFiled();
        platformTF.setBounds(130, 120, 270, 30);

        TextLabel userNameLabel = new TextLabel("用户名：",SwingConstants.RIGHT,17,Color.BLACK);
        userNameLabel.setBounds(40, 180, 85, 30);

        userNameTF = new InputTextFiled();
        userNameTF.setBounds(130, 180, 270, 30);

        TextLabel passwordLabel = new TextLabel("密码：",SwingConstants.RIGHT,17,Color.BLACK);
        passwordLabel.setBounds(40, 240, 85, 30);

        pwdTF = new InputTextFiled();
        pwdTF.setBounds(130, 240, 270, 30);
        pwdTF.addFocusListener(this);

        TextLabel emailLabel = new TextLabel("邮箱：",SwingConstants.RIGHT,17,Color.BLACK);
        emailLabel.setBounds(40, 300, 85, 30);

        emailTF = new InputTextFiled();
        emailTF.setBounds(130, 300, 270, 30);

        TextLabel websiteLabel = new TextLabel("网址：",SwingConstants.RIGHT,17,Color.BLACK);
        websiteLabel.setBounds(40, 360, 85, 30);

        webTF = new InputTextFiled();
        webTF.setBounds(130, 360, 270, 30);

        TextLabel teleLabel = new TextLabel("手机号：",SwingConstants.RIGHT,17,Color.BLACK);
        teleLabel.setBounds(40, 420, 85, 30);

        teleTF = new InputTextFiled();
        teleTF.setBounds(130, 420, 270, 30);

        cancleButton = new FButton("取消", new Color(0,179,254), Color.BLACK, 15);
        cancleButton.setBounds(130, 480, 60, 30);
        cancleButton.addActionListener(this);

        saveButton = new FButton("保存", new Color(0, 179, 254), Color.BLACK, 15);
        saveButton.setBounds(340, 480, 60, 30);
        saveButton.addActionListener(this);
        saveButton.setEnabled(false);

        logoutButton = new FButton();
        logoutButton.setBounds(910, 40, 30, 30);
        logoutButton.setBgImage("sources/images/logout.png");
        logoutButton.setBgTransparency();
        logoutButton.addActionListener(this);


        displayArea = new DisplayArea();
        JScrollPane displayPanel = new JScrollPane(displayArea);
        //displayArea.setBounds(560, 85, 350, 425);
        displayPanel.setBorder(null);
        displayPanel.setBackground(new Color(186, 225, 234));
        displayPanel.setBounds(560, 85, 350, 425);

        bgPanel.addComps(searchTextFiled, searchButton, tipLabel, platformLabel, platformTF,
                userNameLabel, userNameTF, passwordLabel, pwdTF, emailLabel, emailTF, websiteLabel,
                webTF, teleLabel, teleTF, cancleButton, saveButton, logoutButton, displayPanel);

        setVisible(true);
    }

    /**
     * 将输入框里面的内容置空
     * @param inputTextFileds
     */
    private void setContentNull(InputTextFiled...inputTextFileds){

        for (int i = 0; i < inputTextFileds.length; i++ ){
            inputTextFileds[i].setText(null);
        }
        saveButton.setEnabled(false);
    }

    /**
     * 搜索
     */
    private void search(){

        String searchedPlatform = searchTextFiled.getText().toUpperCase();
        if (searchedPlatform.length() > 0) {

            try {
                //searchedPlatform = SetUp.crypto.encrypt(searchedPlatform);
                LinkedList<AccountInfo> accountInfos = SetUp.configParser.getAccountInfoFormConfig(searchedPlatform);
                //accountInfo = SetUp.crypto.decryptAccountInfo(accountInfo);
                displayArea.displayAccountInfo(accountInfos);
            } catch (Exception e1) {
                e1.printStackTrace();
                AttentionPromptBox.prompt("程序运行出错，错误信息：\n" + e1.getMessage(), this);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String platform = platformTF.getText();
        String userName = userNameTF.getText();
        String password = pwdTF.getText();

        /**
         * 保存按钮事件监听处理
         */
        if (e.getSource() == saveButton){
            if (platform.length() > 0 && userName.length() > 0 && password.length() > 0){
                try {
                    //AccountInfo accountInfo = crypto.encryptAccountInfo(new AccountInfo(platform, userName, password, emailTF.getText(), webTF.getText(), teleTF.getText()));
                    AccountInfo accountInfo = new AccountInfo(platform, userNameTF.getEncryptedText(), pwdTF.getEncryptedText(),
                            emailTF.getText(),webTF.getText(), teleTF.getText());
                    configParser.addAccountElements(accountInfo);
                    setContentNull(platformTF, userNameTF, pwdTF, emailTF, webTF, teleTF);
                } catch (Exception e1) {
                    e1.printStackTrace();
                    AttentionPromptBox.prompt("程序运行发生错误：\n" + e1.getMessage() ,this );
                }
            }else {
                AttentionPromptBox.prompt("注册账户平台、账户名、\n密码信息必须完整", this);
            }
            /**
             * 取消按钮事件监听处理
             */
        }else if (e.getSource() == cancleButton) {
            setContentNull(platformTF, userNameTF, pwdTF, emailTF, webTF, teleTF);
            /**
             * 搜索按钮监听事件处理
             */
        }else if (e.getSource() == searchButton) {
            search();
            /**
             * 退出登录按钮事件监听处理
             */
        }else if (e.getSource() == logoutButton) {
            JLabel sureText = new JLabel("清空登录信息及所有账户信息,确认继续？");
            sureText.setForeground(Color.RED);
            sureText.setFont(new Font("黑体", Font.PLAIN, 16));
            int value = JOptionPane.showConfirmDialog(this, sureText, "请确认",
                    JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

            if (value == JOptionPane.YES_OPTION){
                SetUp.configParser.deleteConfigFile();
                System.exit(0);
            }else {
                return;
            }
        }
    }

    @Override
    public void focusGained(FocusEvent e) {

            if (e.getSource() == pwdTF) {
            if (platformTF.getText().length() > 0 && userNameTF.getText().length() >0)
                saveButton.setEnabled(true);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == searchButton){
            //鼠标右击
            if (e.getButton() == MouseEvent.BUTTON3) {
                try {
                    displayArea.displayAccountInfo(SetUp.configParser.getAllAccountInfoFromConfig());
                } catch (Exception e1) {
                    e1.printStackTrace();
                    AttentionPromptBox.prompt("加载文件发生错误，错误：" + e1.getMessage(), this);
                }
            }

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            search();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
