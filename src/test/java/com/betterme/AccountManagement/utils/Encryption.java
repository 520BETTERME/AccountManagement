package com.betterme.AccountManagement.utils;

import java.util.HashMap;
import java.util.Map;

public class Encryption {

        //  String str;
        static Map<Character,Character> map=new HashMap<Character,Character>();
        static Map<Character,Character> map1=new HashMap<Character,Character>();
        Encryption(){
            String a="abcdefghijklmnopqrstuvwsvz*";
            String b="veknohzf*iljxdmygbrcswqupta";
            for(int i=0;i<a.length();i++){
                map.put(a.charAt(i),b.charAt(i));
                map1.put(b.charAt(i),a.charAt(i));
            }
        }
        public  static String encrypt(String str){
            String s2= str.toLowerCase();
            String d = "";
            for (int i = 0; i < s2.length(); i++) {
                d = d + map.get(s2.charAt(i));
            }
            return d;
        }
        public static String decrypt(String str){
            String d = "";
            for (int i = 0; i < str.length(); i++) {
                d = d + map1.get(str.charAt(i));
            }
            return d;
        }

        public static void main(String[] args){
            Encryption e1=new Encryption();
            String stre="赵卫华";
            String stre1=e1.encrypt(stre);
            System.out.println(stre1);
            System.out.println(e1.decrypt(stre1));
        }

}
