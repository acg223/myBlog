package com.example.blog.Service;

//import com.springboot.webboot.Bean.Userall;
//import com.springboot.webboot.Bean.Userinfo;
import com.example.blog.Bean.Userinfo;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public interface UserInfoService {
//    List<Userall> fenye(int page,int size,Userall userall);
    Userinfo getInfos(int uid);
    int addUserinfo(int uid);
    int addTweetsNum(int uid);
    void updateBirthday(Timestamp date, int uid);
    void updateUlogo(String ulogo,int uid);
    void updateAbout(String about,int uid);
}
