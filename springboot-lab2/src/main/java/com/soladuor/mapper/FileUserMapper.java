package com.soladuor.mapper;

import com.soladuor.pojo.FileUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileUserMapper {
    /**
     * 删除
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 增加
     */
    int insert(FileUser record);

    /**
     * 查询
     */
    FileUser selectByPrimaryKey(Integer id);

    /**
     * 查询全部
     */
    List<FileUser> selectAll();

    /**
     * 修改
     */
    int updateByPrimaryKey(FileUser record);
}
