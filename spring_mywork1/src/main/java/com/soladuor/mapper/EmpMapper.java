package com.soladuor.mapper;

import com.soladuor.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface EmpMapper {

    @Insert("INSERT INTO " +
        "spring_work1_emp(`emp_id`,`e_name`) " +
        "VALUES (#{empId},#{eName})")
    int insert(Emp emp);

    @Update("UPDATE spring_work1_emp SET " +
        "`e_name` = #{eName} " +
        "WHERE `emp_id` = #{empId}")
    int update(Emp emp);

    @Delete("DELETE FROM spring_work1_emp WHERE emp_id = #{empId}")
    int delete(String empId);

    @Select("SELECT " +
        "`emp_id`,`e_name` " +
        "FROM spring_work1_emp")
    List<Emp> selectAll();

    @Select("SELECT " +
        "`emp_id`,`e_name` " +
        "FROM spring_work1_emp WHERE emp_id = #{id}")
    Emp selectById(String id);

}
