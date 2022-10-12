package com.example.blog.Mapper;

//import com.springboot.webboot.Bean.Userall;
import com.example.blog.Bean.Userinfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface UserInfoMapper {
//    @Select("select * from userall where urelname like #{userall.urealname} " +
//            "or uaite like #{userall.uaite} limit #{page},#{size}")
//    List<Userall> fenye(int page,int size,Userall userall);
    @Select("select * from userinfo where uid=#{uid} limit 1")
    Userinfo getInfos(int uid);
    @Insert("insert into userinfo(uid) values (#{uid})")
    int addUserinfo(int uid);
    @Update("update userinfo set utweet=(utweet+1) where uid=#{uid}")
    int addTweetsNum(int uid);
    @Update("update userinfo set udate=#{date} where uid=#{uid}")
    void updateBirthday(Timestamp date, int uid);
    @Update("update userinfo set ulogo=#{ulogo} where uid=#{uid}")
    void updateUlogo(String ulogo, int uid);
    @Update("update userinfo set uabout=#{about} where uid=#{uid}")
    void updateAbout(String about, int uid);
}
