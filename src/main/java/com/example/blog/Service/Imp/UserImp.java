package com.example.blog.Service.Imp;

//import com.springboot.webboot.Bean.Signin;
import com.example.blog.Bean.Users;
import com.example.blog.Mapper.UserMapper;
import com.example.blog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserImp implements UserService
{
    @Autowired
    UserMapper userMapper;
//    public Users getAcc(int id){
//       return userMapper.getAcc(id);
//    }
    //登录检查
    public Users CheckLogin(String username,String password){
        Users users=userMapper.CheckLogin(username);
        if (users!=null){
            if (password.equals(users.getUpwd())){
                return users;
            }else {
                return null;
            }
        }else {
            return null;
        }

    }

    @Override
    public int getDayUp(String time) {
        return 0;
    }

    //查找用户
    public Users QueryUserById(int id){
        Users users=userMapper.QueryUserById(id);
        return  users;
    }
    //查找在线用户数
    public int getUserNum(){
        return userMapper.queryUserNum();
    }

    @Override
    public Users CheckUserName(String username){
        Users users=userMapper.CheckUserName(username);
        if (users!=null){
            return users;
        }
        return null;
    }

    //检查登录邮箱是否冲突
    public Users CheckUserEmail(String username){
        Users users=userMapper.CheckUserName(username);
        if (users!=null){
            return users;
        }
        return null;
    }
    //添加用户，即注册
    public void addUser(Users users){
        userMapper.addUser(users.getUemail(), users.getUpwd(),users.getUaite() , users.getUtime());
    }
    //检查昵称
    public boolean CheckUaite(String uaite){
        List<Users> list=userMapper.CheckUaite(uaite);
        if (list.size()>0){
            return true;
        }
        return false;
    }
    //检查在线状态
    public void updateOnline(int uonline,int uid){
        userMapper.updateOnline(uonline,uid);
    }
    //更新用户状态
    public void updateState(String uid,String state){
        userMapper.updateState(uid,state);
    }
}
