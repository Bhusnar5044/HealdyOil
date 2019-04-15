package com.example.healdyoilnp;

public class Userprofile {

    public String name;
    public String mob;
    public String mail;
    public String pwd;
    public String addr;

   public Userprofile(){

   }

    public Userprofile(String name, String mob, String mail, String pwd,String addr ) {
        this.name = name;
        this.mail = mail;
        this.mob = mob;
        this.pwd = pwd;
        this.addr=addr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
