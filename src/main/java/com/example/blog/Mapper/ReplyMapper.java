package com.example.blog.Mapper;

import com.example.blog.Bean.Reply;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
@Mapper
public interface ReplyMapper {
  @Select("select * from reply where tid=#{tid} order by rpTime desc ")
    List<Reply> getReplyByTid(int tid);
}
