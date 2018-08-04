package com.betterme.AccountManagement.ui;

import com.betterme.AccountManagement.AccountInfo;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class DisplayArea extends JTextArea{

//    private final String SEGMENTING_LINE = "-------华丽的分割线------";
    private final String PLATFORM = "注册平台：";
    private final String USER_NAME = "用户名：";
    private final String PASSWORD = "密码：";
    private final String EMAIL = "邮箱：";
    private final String WEBSITE = "网址：";
    private final String TELEPHONE = "电话：";
    
    public DisplayArea(){

        //this.setOpaque(false);
        this.setBackground(new Color(186, 225, 234));
        this.setFont(new Font("黑体", Font.PLAIN, 18));
        this.setEditable(false);
    }

    /**
     * 显示Account的信息
     * @param accountInfos
     */
    public void displayAccountInfo(LinkedList<AccountInfo> accountInfos){
        
//        System.out.println("调用了显示函数。。。");
//        System.out.println(accountInfos.size());
        if (this.getText() != null)
            this.setText(null);

        if (accountInfos != null) {
            for (AccountInfo accountInfo : accountInfos) {
                //System.out.println("调用了打印信息的函数。。。。");
                this.append(PLATFORM + accountInfo.getPlarform() + "\n");
                this.append(USER_NAME + accountInfo.getUserName() + "\n");
                this.append(PASSWORD + accountInfo.getPassword() + "\n");
                this.append(EMAIL + accountInfo.getEmail() + "\n");
                this.append(WEBSITE + accountInfo.getWebsite() + "\n");
                this.append(TELEPHONE + accountInfo.getTelephone() + "\n\n");
//                if (accountInfos.size() > 1)
//                    this.append(SEGMENTING_LINE);
            }
        }else {
            this.setText("查无结果!");
        }
    }
}
