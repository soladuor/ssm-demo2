package com.soladuor.mapper;

import com.soladuor.pojo.Log;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogMapper {

    @Insert("INSERT INTO " +
        "ssm_lab1_log(`log_id`,`datetime`,`desc`) " +
        "VALUES (#{logId},#{datetime},#{desc})")
    int insert(Log log);

    @Update("UPDATE ssm_lab1_log SET " +
        "`datetime` = #{log.datetime}," +
        "`desc` = #{log.desc} " +
        "WHERE log_id = #{logId}")
    int update(Log log);

    @Delete("DELETE FROM ssm_lab1_log WHERE log_id = #{logId}")
    int delete(String logId);

    @Select("SELECT `log_id`,`datetime`,`desc` FROM ssm_lab1_log")
    List<Log> selectAll();
}
