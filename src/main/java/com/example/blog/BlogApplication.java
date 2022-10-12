package com.example.blog;

import com.example.blog.Bean.Blog;
import com.example.blog.Controller.BlogControler;
import com.example.blog.Service.BlogService;
import com.example.blog.Service.Imp.BlogImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

}
