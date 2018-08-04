package com.betterme.AccountManagement.utils;

import java.io.IOException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import com.betterme.AccountManagement.AccountInfo;
import com.betterme.AccountManagement.User;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * DES加密 解密算法
 * @author zhangdi
 */
public class Crypto {

    private final String DES = "DES";
    private final String ENCODE = "GBK";
    private final String DEFAULT_KEY = "BetterMe";

    /**
     * 使用 默认key 加密
     * @param data 待加密数据
     * @return
     * @throws Exception
     */
    public String encrypt(String data) throws Exception {
        byte[] bt = encrypt(data.getBytes(ENCODE), DEFAULT_KEY.getBytes(ENCODE));
        String strs = new BASE64Encoder().encode(bt);
        return strs;
    }

    /**
     * 使用 默认key 解密
     * @param data 待解密数据
     * @return
     * @throws IOException
     * @throws Exception
     */
    public String decrypt(String data) throws IOException, Exception {

        if (data == null)
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] buf = decoder.decodeBuffer(data);
        byte[] bt = decrypt(buf, DEFAULT_KEY.getBytes(ENCODE));
        return new String(bt, ENCODE);
    }

    /**
     * Description 根据键值进行加密
     * @param data
     * @param key
     *            加密键byte数组
     * @return
     * @throws Exception
     */
    private byte[] encrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(DES);

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }

    /**
     * Description 根据键值进行解密
     * @param data
     * @param key 加密键byte数组
     * @return
     * @throws Exception
     */
    private byte[] decrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(DES);

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }

    /**
     * 对账户信息解密
     * @param accountInfo
     * @return
     * @throws Exception
     */
    public AccountInfo decryptAccountInfo(AccountInfo accountInfo) throws Exception {
        if (accountInfo != null) {
            return new AccountInfo(accountInfo.getPlarform(), decrypt(accountInfo.getUserName()),
                    decrypt(accountInfo.getPassword()), accountInfo.getEmail(),
                    accountInfo.getWebsite(), accountInfo.getTelephone());
        }
        return null;
    }


//    public static void main(String[] args){
//
//        Crypto crypto = new Crypto();
//        String data = "赵卫华";
//        System.out.println("加密前===>"+data);
//        try {
//            String jiamihou = crypto.encrypt(data);
//            System.err.println("加密后===>"+jiamihou);
//            System.err.println("解密后===>"+crypto.decrypt(jiamihou));
//        } catch (Exception e) {
//
//            //e.printStackTrace();
//        }
//        try {
//            String resoult = SetUp.crypto.decrypt("9vmcZp/9yb0KP/GRwo5NxA==");
//            System.out.println(resoult);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
}
