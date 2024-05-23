package com.soladuor.mapper;

import com.soladuor.pojo.FileComment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileCommentMapper {
    /**
     * 删除
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 增加
     */
    int insert(FileComment record);

    /**
     * 查询
     */
    FileComment selectByPrimaryKey(Integer id);

    /**
     * 查询全部
     */
    List<FileComment> selectAll();

    /**
     * 修改
     */
    int updateByPrimaryKey(FileComment record);
}
