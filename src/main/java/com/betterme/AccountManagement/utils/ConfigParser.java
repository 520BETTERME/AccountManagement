package com.betterme.AccountManagement.utils;

import com.betterme.AccountManagement.AccountInfo;
import com.betterme.AccountManagement.SetUp;
import com.betterme.AccountManagement.User;

import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;


public class ConfigParser {

    private final String CONFIG_PATH = "sources/config.xml";
    private final String COMMENT = "不要动本文件，否则账户密码信息会丢失！";
    private final String ROOT_ELEMENT = "config";
    private final String USER_ELEMENT = "user";
    public static final String PASSWORD_ELEMENT = "password";
    private final String SAFE_PROBLEM_ELEMENT = "safe-problem";
    public static final String ANSWER_ELEMENT = "answer";
//    private final String FIRST_USE_ELEMENT = "first-use";
    private final String LAST_EDIT__TIME_ELEMENT = "last-edit-time";
    private final String ACCOUNT_INFO_ELEMENTS = "account-info";
//    private final String ACCOUNT_ELEMENT = "account";
    private final String ID_ATTRIBUTE = "id";
    private final String PLATFORM_ELEMENT = "platform";
    private final String USER_NAME_ELEMENT = "user-name";
    private final String ACCOUNT_PASSWORD_ELEMENT = "account-password";
    private final String EMAIL_ELEMENT = "email";
    private final String WEBSITE_ELEMENT = "website";
    private final String TELEPHONE_ELEMENT = "telephone";
    private final File CONFIG_FILE = new File(CONFIG_PATH);

    private OutputStreamWriter out;
    private XMLWriter xmlWriter;
    private Crypto crypto;

    public ConfigParser() {
        this.crypto = new Crypto();
    }

    //private static Document document = DocumentHelper.createDocument();

    /**
     * 程序运行时检查是否有config.xml文件
     * @return 是否有
     */
    public boolean isHaveConfigFile() throws IOException{

        boolean isHave = false;
        //如果文件不存在，则创建文件
        if (!CONFIG_FILE.exists()) {
            CONFIG_FILE.createNewFile();
        }else {
            isHave = true;
        }
        return isHave;
    }

    /**
     * 删除配置文件
     */
    public void deleteConfigFile(){

        if(CONFIG_FILE.exists() && CONFIG_FILE.isFile())
            CONFIG_FILE.delete();
    }

    /**
     * 向xml文件中添加元素
     * @param document
     * @throws IOException
     */
    private void configWriter(Document document) throws IOException {

        //格式化xml文件
        OutputFormat outputFormat = OutputFormat.createPrettyPrint();
        //设置编码格式
        outputFormat.setEncoding("utf-8");
        //写入xml文件

        //创建一个输出流
        OutputStream outputStream = new FileOutputStream(CONFIG_FILE);
        //设置编码，防止乱码
        out = new OutputStreamWriter(outputStream, "utf-8");
        //dom4j创建一个xml对象
        xmlWriter = new XMLWriter(out, outputFormat);
        xmlWriter.write(document);
        //关闭流
        xmlWriter.close();
        out.close();
    }

    /**
     * 程序第一次运行时创建config.xml文件
     //* @param comp 调用此方法的Component
     */
    public void createConfigXML() throws IOException{

        Document document = DocumentHelper.createDocument();
        //添加注释
        document.addComment(COMMENT);
        //创建根节点
        Element root = document.addElement(ROOT_ELEMENT);
        configWriter(document);
    }

    /**
     * 程序第一次运行时检查是否添加新的用户
     * @return 是否添加
     * @throws DocumentException
     */
    public boolean isAddUser() throws DocumentException {

        boolean isAdded = true;
        Document doc = getDocument(CONFIG_PATH);
        Element element = doc.getRootElement().element(USER_ELEMENT);
        if (element == null) {
            isAdded = false;
        }
        return isAdded;
    }

    /**
     * 将加密后的用户信息写入到xml文件中
     * @param user
     * @throws DocumentException
     */
    public void addUserElements(User user) throws DocumentException, IOException {
        
        Document doc = getDocument(CONFIG_PATH);
        Element root = doc.getRootElement();
        Element userEle = root.addElement(USER_ELEMENT);
        Element pwdEle = userEle.addElement(PASSWORD_ELEMENT);
        pwdEle.setText(user.getPwd());
        Element spEle = userEle.addElement(SAFE_PROBLEM_ELEMENT);
        spEle.setText(user.getSafetyProblem());
        Element answerEle = userEle.addElement(ANSWER_ELEMENT);
        answerEle.setText(user.getGetSafetyProblemAnswer());
        //Element firstUseEle = userEle.addElement(FIRST_USE_ELEMENT);
        Element lastEditEle = userEle.addElement(LAST_EDIT__TIME_ELEMENT);
        lastEditEle.setText(getSysTime());
        configWriter(doc);
    }

    /**
     * 向xml文件中写入加密后的Account信息
     * @param accountInfo
     * @throws DocumentException
     * @throws IOException
     */
    public void addAccountElements(AccountInfo accountInfo) throws DocumentException, IOException {

        Document doc = getDocument(CONFIG_PATH);
        Element root = doc.getRootElement();

        String s1 = accountInfo.getPlarform() + accountInfo.getUserName();
        String id = getSameAccountID(s1);
        if (id != null) {
            String xpanth = "//" + ACCOUNT_INFO_ELEMENTS + "[@id='" + id + "']";
            //System.out.println(xpanth);
            Element accountInfoEle2 = (Element) root.selectSingleNode(xpanth);
            accountInfoEle2.element(PLATFORM_ELEMENT).setText(accountInfo.getPlarform());
            accountInfoEle2.element(USER_NAME_ELEMENT).setText(accountInfo.getUserName());
            accountInfoEle2.element(ACCOUNT_PASSWORD_ELEMENT).setText(accountInfo.getPassword());
            accountInfoEle2.element(EMAIL_ELEMENT).setText(accountInfo.getEmail());
            accountInfoEle2.element(WEBSITE_ELEMENT).setText(accountInfo.getWebsite());
            accountInfoEle2.element(TELEPHONE_ELEMENT).setText(accountInfo.getTelephone());
        } else {
            Element accountInfoEle = root.addElement(ACCOUNT_INFO_ELEMENTS);
            accountInfoEle.addAttribute(ID_ATTRIBUTE, getSysTime());
            Element platformEle = accountInfoEle.addElement(PLATFORM_ELEMENT);
            platformEle.setText(accountInfo.getPlarform());
            Element userNameEle = accountInfoEle.addElement(USER_NAME_ELEMENT);
            userNameEle.setText(accountInfo.getUserName());
            Element accountPwdEle = accountInfoEle.addElement(ACCOUNT_PASSWORD_ELEMENT);
            accountPwdEle.setText(accountInfo.getPassword());
            Element emailEle = accountInfoEle.addElement(EMAIL_ELEMENT);
            emailEle.setText(accountInfo.getEmail());
            Element websiteEle = accountInfoEle.addElement(WEBSITE_ELEMENT);
            websiteEle.setText(accountInfo.getWebsite());
            Element telephoneEle = accountInfoEle.addElement(TELEPHONE_ELEMENT);
            telephoneEle.setText(accountInfo.getTelephone());
        }
        configWriter(doc);
    }

    /**
     * 返回配置文件中与输入的账户信息具有相同平台及用户名的账户id
     * s 平台和用户名字符串
     * @return
     * @throws DocumentException
     */
    private String getSameAccountID(String s) throws DocumentException {

        Document doc = getDocument(CONFIG_PATH);
        List<Element> accountEles = doc.selectNodes("//" + ACCOUNT_INFO_ELEMENTS);
        String id = null;
        for (Element element: accountEles) {
            String s1 = element.element(PLATFORM_ELEMENT).getText() + element.element(USER_NAME_ELEMENT).getText();
            if (s.toUpperCase().equals(s1.toUpperCase())) {
                id = element.attributeValue(ID_ATTRIBUTE);
            }else
                return null;
        }
        return id;
    }

    /**
     * 验证输入的密码、回答是否正确
     * @param text
     * @param elementName
     * @return
     */
    public boolean isCorrectInput(String text, String elementName) throws Exception {

        boolean isCorrect = false;
        Document document = getDocument(CONFIG_PATH);
        Element pwdEle = document.getRootElement().element(USER_ELEMENT).element(elementName);
        if (pwdEle.getText().equals(text)) {
            isCorrect = true;
        }
        return isCorrect;
    }

    /**
     * 得到加密的问题
     * @return
     * @throws DocumentException
     */
    public String getSafeProblem() throws DocumentException {

        Document doc = getDocument(CONFIG_PATH);
        return doc.getRootElement().element(USER_ELEMENT).element(SAFE_PROBLEM_ELEMENT).getText();
    }

    /**
     * 重置登录密码
     * @param text
     * @throws DocumentException
     */
    public void resetPassword(String text) throws DocumentException, IOException {

        Document doc = getDocument(CONFIG_PATH);
        Element pwdElement = doc.getRootElement().element(USER_ELEMENT).element(PASSWORD_ELEMENT);
        pwdElement.setText(text);
        configWriter(doc);
    }

    /**
     * 根据platform返回解密后的账户信息（可能不止一个）
     * @param platform
     * @return
     */
    public LinkedList<AccountInfo> getAccountInfoFormConfig(String platform) throws Exception {

        Document doc = getDocument(CONFIG_PATH);
        List<Element> platforms =  doc.selectNodes("//" + PLATFORM_ELEMENT);
        //集合用来存符合搜索结果的具有相同值的元素
        LinkedList<Element> searchedPlatforms = new LinkedList<Element>();
        LinkedList<AccountInfo> accountInfos = new LinkedList<AccountInfo>();
        if (platforms.size() != 0) {

            for (Element element: platforms) {
                if (element.getText().toUpperCase().equals(platform)) {
                    searchedPlatforms.add(element);
                }
            }
            if (searchedPlatforms.size() != 0) {
                for (Element element : searchedPlatforms) {
                    Element accountEle = element.getParent();
                    AccountInfo accountInfo = new AccountInfo(accountEle.element(PLATFORM_ELEMENT).getText(),
                            SetUp.crypto.decrypt(accountEle.element(USER_NAME_ELEMENT).getText()),
                            SetUp.crypto.decrypt(accountEle.element(ACCOUNT_PASSWORD_ELEMENT).getText()),
                            accountEle.element(EMAIL_ELEMENT).getText(),
                            accountEle.element(WEBSITE_ELEMENT).getText(),
                            accountEle.element(TELEPHONE_ELEMENT).getText());
                    accountInfos.add(accountInfo);
                }
            }else
                return null;

        }else {
            return null;
        }
        return accountInfos;
    }

    /**
     * 得到所有的账户信息
     * @return 账户信息的对象数组
     * @throws DocumentException
     */
    public LinkedList<AccountInfo> getAllAccountInfoFromConfig() throws Exception {

        LinkedList<AccountInfo> accountInfos = new LinkedList<AccountInfo>();
        Document doc = getDocument(CONFIG_PATH);
        List<Element> accountEles = doc.selectNodes("//" + ACCOUNT_INFO_ELEMENTS);
        if (accountEles.size() != 0) {
            for (Element element : accountEles) {
                AccountInfo accountInfo = new AccountInfo(element.element(PLATFORM_ELEMENT).getText(),
                        SetUp.crypto.decrypt(element.element(USER_NAME_ELEMENT).getText()),
                        SetUp.crypto.decrypt(element.element(ACCOUNT_PASSWORD_ELEMENT).getText()),
                        element.element(EMAIL_ELEMENT).getText(),
                        element.element(WEBSITE_ELEMENT).getText(),
                        element.element(TELEPHONE_ELEMENT).getText());
                accountInfos.add(accountInfo);
            }
            return accountInfos;
        }else
            return null;
    }



    /**
     * 返回document，xml文件对象
     * @param xmlPath
     * @return
     * @throws DocumentException
     */
    private Document getDocument(String xmlPath) throws DocumentException {

        SAXReader saxReader = new SAXReader();
        return saxReader.read(xmlPath);
    }


    /**
     * 得到系统时间以字符串形式返回
     * @return
     */
    private String getSysTime(){

        Calendar calendar  = Calendar.getInstance();

        String time = String.valueOf(calendar.get(Calendar.YEAR)) +
                String.valueOf(calendar.get(Calendar.MONTH)+1) +
                String.valueOf(calendar.get(Calendar.DATE)) +
                String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)) +
                String.valueOf(calendar.get(Calendar.MINUTE)) +
                String.valueOf(calendar.get(Calendar.SECOND));
        return time;
    }
}
