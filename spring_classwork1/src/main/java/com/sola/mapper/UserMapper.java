package com.sola.mapper;

import com.sola.pojo.Classwork1User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    // 创建一个数据库的操作接口
    @Select("SELECT * FROM classwork1_user")
    public List<Classwork1User> selectAll();
}
