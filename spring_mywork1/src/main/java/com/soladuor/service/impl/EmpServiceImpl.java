package com.soladuor.service.impl;

import com.soladuor.mapper.EmpMapper;
import com.soladuor.pojo.Emp;
import com.soladuor.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public int insert(Emp emp) {
        return empMapper.insert(emp);
    }

    @Override
    public int delete(String id) {
        return empMapper.delete(id);
    }

    @Override
    public int update(Emp emp) {
        return empMapper.update(emp);
    }

    @Override
    public List<Emp> selectAll() {
        return empMapper.selectAll();
    }

    @Override
    public Emp selectById(String empId) {
        return empMapper.selectById(empId);
    }
}
