package com.soladuor.mapper;

import com.soladuor.pojo.FileMessage;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileMessageMapper {
    /**
     * 删除
     */
    int deleteByPrimaryKey(String id);

    /**
     * 增加
     */
    int insert(FileMessage record);

    /**
     * 查询
     */
    FileMessage selectByPrimaryKey(String id);

    /**
     * 查询全部
     */
    List<FileMessage> selectAll();

    /**
     * 修改
     */
    int updateByPrimaryKey(FileMessage record);

    /**
     * 文件下载次数加1
     */
    @Update("UPDATE ssm_lab1_file_message SET download_count=download_count+1 WHERE id=#{id}")
    void incDownCount(String id);
}
