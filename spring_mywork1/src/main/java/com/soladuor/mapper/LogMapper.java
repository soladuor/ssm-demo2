package com.soladuor.mapper;

import com.soladuor.pojo.Log;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface LogMapper {

    @Insert("INSERT INTO " +
        "spring_work1_log(`log_id`,`datetime`,`desc`) " +
        "VALUES (#{logId},#{datetime},#{desc})")
    int insert(Log log);

    @Update("UPDATE spring_work1_log SET " +
        "`datetime` = #{log.datetime}," +
        "`desc` = #{log.desc} " +
        "WHERE log_id = #{logId}")
    int update(Log log);

    @Delete("DELETE FROM spring_work1_log WHERE log_id = #{logId}")
    int delete(String logId);

    @Select("SELECT " +
        "`log_id`,`datetime`,`desc` " +
        "FROM spring_work1_log")
    List<Log> selectAll();
}
