package com.example.popular.domain;

public class User {
    private Integer userId;

    private String userName;

    private String password;

    private String salt;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 判断是否是admin用户
     * @return
     */
    public boolean isAdmin()
    {
        return isAdmin(this.userId);
    }

    /**
     * 编号为1的为admin
     * @param userId
     * @return
     */
    public static boolean isAdmin(Integer userId)
    {
        return userId != null && 1 == userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }
}