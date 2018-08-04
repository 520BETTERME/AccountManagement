package com.betterme.AccountManagement;

public class User {

    private String pwd;
    private String safetyProblem;
    private String safetyProblemAnswer;
    private boolean firstUse;

    public User(String pwd, String safetyProblem, String safetyProblemAnswer) {
        this.pwd = pwd;
        this.safetyProblem = safetyProblem;
        this.safetyProblemAnswer = safetyProblemAnswer;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSafetyProblem() {
        return safetyProblem;
    }

    public void setSafetyProblem(String safetyProblem) {
        this.safetyProblem = safetyProblem;
    }

    public String getGetSafetyProblemAnswer() {
        return safetyProblemAnswer;
    }

    public void setGetSafetyProblemAnswer(String getSafetyProblemAnswer) {
        this.safetyProblemAnswer = getSafetyProblemAnswer;
    }

    public boolean isFirstUse() {
        return firstUse;
    }

    public void setFirstUse(boolean firstUse) {
        this.firstUse = firstUse;
    }
}
