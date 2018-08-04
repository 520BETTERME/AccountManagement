package com.betterme.AccountManagement;

import com.betterme.AccountManagement.ui.DisplayArea;
import com.betterme.AccountManagement.ui.FirstLoginFrame;
import com.betterme.AccountManagement.ui.LoginFrame;
import com.betterme.AccountManagement.utils.ConfigParser;
import com.betterme.AccountManagement.utils.Crypto;

public class SetUp {

    public static Crypto crypto = new Crypto();
    public static ConfigParser configParser = new ConfigParser();
    //public static DisplayArea displayArea = new DisplayArea();

    public static void main(String[] args){

        try {
            ConfigParser configParser = new ConfigParser();
            boolean notFirstUse = configParser.isHaveConfigFile();
            if (!notFirstUse) {
                configParser.createConfigXML();
                new FirstLoginFrame().init2();
            }else {
                /**
                 * 不是第一次运行，要检查是否添加了登录信息，否则
                 * 跳出设置登录信息的窗口
                 */
                boolean isAddedUser = configParser.isAddUser();
                if (notFirstUse && isAddedUser) {
                    new LoginFrame();
                }else
                    new FirstLoginFrame().init2();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
