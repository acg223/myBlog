package com.example.blog.Bean;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Userinfo {
    private int id;
    private int uid;
    private String uaddress;
    private String uabout;
    private Timestamp udate;
    private String ulogo;
    private String ubg;
    private int ufans;
    private int utweet;
    private int ufollow;
    private String ucolor;
}
