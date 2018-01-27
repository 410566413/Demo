package com.hawker.yangtianqi.demo.model;

/**
 * Created by yangtianqi on 2018/1/27.
 */
public class Person {
    private String userName;
    private String sex;
    private int age=0;
    private int iconId;

    public Person(String userName,String sex,int age){
        this.userName= userName;
        this.sex = sex;
        this.age = age;
    }

    public Person(String userName,String sex,int age,int iconId){
        this.userName= userName;
        this.sex = sex;
        this.age = age;
        this.iconId=iconId;
    }

    public String toString(){
        return userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }
}
