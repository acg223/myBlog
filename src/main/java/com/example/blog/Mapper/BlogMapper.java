package com.example.blog.Mapper;

import com.example.blog.Bean.Blog;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
@Repository
@Mapper
public interface BlogMapper {
    @Insert("insert into blog (uid,bcontent,bpic,btime) values (#{uid},#{bcontent}," +
            "#{bpic},#{btime})")
    @Options(useGeneratedKeys=true, keyProperty="tid", keyColumn="tid")
    void addBlog(Blog blog);
    @Select("select * from blog order by btime")
    List<Blog> getBlogALL();
    @Select("select count(*) from likes where tid=#{tid} ")
    int sumlike(int tid);
    @Update(("update blog set blike=#{bnum} where tid=#{tid}"))
    void setLike(int bnum,int tid);
    @Select("select count(*) from forward where tid=#{tid} ")
    int sumforward(int tid);
    @Update(("update blog set bforward=#{bnum} where tid=#{tid}"))
    void setforward(int bnum,int tid);
    @Select("select count(*) from likes where tid=#{tid} and uid=#{uid}")
    int liken(int uid,int tid);
    @Select("select count(*) from forward where tid=#{tid} and uid=#{uid}")
    int zhuaned(int uid,int tid);
    @Insert("insert into likes(uid,tid,ltime) values (#{uid},#{tid},#{ltime})")
    void addLike(int uid,int tid,Timestamp ltime);
    @Delete("delete from likes where tid=#{tid} and uid=#{uid}")
    void delLike(int uid,int tid);

}
