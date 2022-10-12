package com.example.blog.Service.Imp;

import com.example.blog.Bean.Userinfo;
import com.example.blog.Mapper.UserInfoMapper;
import com.example.blog.Service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class UserInfoImp implements UserInfoService {
    @Autowired
    UserInfoMapper userInfoMapper;
//    public List<Userall> fenye(int page,int size,Userall userall){
//        List<Userall> useralls;
//        useralls=userInfoMapper.fenye(page,size,userall);
//        return useralls;
//    }
    public Userinfo getInfos(int uid){
        return userInfoMapper.getInfos(uid);
    }
    public int addUserinfo(int uid){
        int n=userInfoMapper.addUserinfo(uid);
        return n;
    }

    public int addTweetsNum(int uid) {
        return userInfoMapper.addTweetsNum(uid);
    }

    @Override
    public void updateBirthday(Timestamp date, int uid) {
        userInfoMapper.updateBirthday(date, uid);
    }

    @Override
    public void updateUlogo(String ulogo, int uid) {
        userInfoMapper.updateUlogo(ulogo, uid);
    }
    public void updateAbout(String about,int uid){
        userInfoMapper.updateAbout(about, uid);
    }

}
