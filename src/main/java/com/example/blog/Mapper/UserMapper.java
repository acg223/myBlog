package com.example.blog.Mapper;

import com.example.blog.Bean.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface UserMapper {
//     Users getAcc(Integer id);
//     @Select("select * from users where uemail=#{username} or uaite=#{username} limit 1")
     Users CheckLogin(String username);
     @Select("select * from users where uid=#{id} limit 1")
     Users QueryUserById(int id);
     @Select("select count (*) from users where state !=0")
     int queryUserNum();
     @Select("select * from users where uemail=#{username} limit 1")
     Users  CheckUserName(String username);
     @Insert("insert into users(uemail,upwd,uaite,utime) values (#{uemail},#{password},#{aite},#{utime})")
     void addUser(String uemail, String password, String aite, Timestamp utime);
     @Select("select * from users where uaite=#{uaite} limit 1")
     List<Users> CheckUaite(String uaite);
     @Update("update users set uonline=#{uonline} where uid=#{uid}")
     void updateOnline(int uonline,int uid);
     @Update("update users set ustate =#{state} where uid=#{uid}")
     void updateState(String uid,String state);
}

