package com.example.blog.Service;

import com.example.blog.Bean.Users;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

Users CheckLogin(String username,String password);
int getDayUp(String time);
Users QueryUserById(int id);
int getUserNum();
Users CheckUserName(String username);
void addUser(Users users);
boolean CheckUaite(String uaite);
void updateOnline(int uonline,int uid);
void updateState(String uid,String state);
}
