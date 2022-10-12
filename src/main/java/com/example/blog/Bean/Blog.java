package com.example.blog.Bean;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Blog {
    private int tid;
    private int uid;
    private String bcontent;
    private String bpic;
    private int blike;
    private int btzhuan;
    private Timestamp btime;

}
