package com.betterme.AccountManagement.utils;

import com.betterme.AccountManagement.User;
import org.dom4j.DocumentException;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ConfigParserTest {

    @Test
    void createConfigXML() {

        User user = new User("123456", "我爱你？", "不爱");
        ConfigParser configParser = new ConfigParser();
        try {
            configParser.addUserElements(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
//    @Test
//    public void test2(){
//        System.out.println(new ConfigParser().getSysTime());
//    }
}