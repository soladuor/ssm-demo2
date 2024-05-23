package com.soladuor.service;

import com.soladuor.pojo.Emp;

import java.util.List;

public interface EmpService {

    int insert(Emp emp);

    int delete(String id);

    int update(Emp emp);

    List<Emp> selectAll();

    Emp selectById(String empId);

}
