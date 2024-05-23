package com.soladuor.mapper;

import com.soladuor.pojo.FileSpace;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileSpaceMapper {
    /**
     * 删除
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 增加
     */
    int insert(FileSpace record);

    /**
     * 查询
     */
    FileSpace selectByPrimaryKey(Integer id);

    /**
     * 根据用户id外键查询
     */
    @Select("SELECT id, user_id, space_size FROM ssm_lab1_file_space WHERE user_id = #{userId,jdbcType=INTEGER}")
    FileSpace selectByUserId(Integer userId);

    /**
     * 查询全部
     */
    List<FileSpace> selectAll();

    /**
     * 修改
     */
    int updateByPrimaryKey(FileSpace record);

    /**
     * 改变空间（增加或减少）
     */
    @Update("UPDATE ssm_lab1_file_space" +
        " SET space_size = space_size + #{size,jdbcType=DOUBLE}" +
        " WHERE id = #{spaceId,jdbcType=INTEGER}")
    int updateSpaceSize(@Param("spaceId") int spaceId, @Param("size") double size);

}
