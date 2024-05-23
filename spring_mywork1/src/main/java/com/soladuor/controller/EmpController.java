package com.soladuor.controller;

import com.soladuor.pojo.Emp;
import com.soladuor.service.EmpService;
import com.soladuor.utils.result.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emp")
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping("/get")
    public JSONResult getAll() {
        List<Emp> ems = empService.selectAll();
        return JSONResult.ok(ems);
    }

    @GetMapping("/get/{id}")
    public JSONResult get(@PathVariable String id) {
        Emp emp = empService.selectById(id);
        return JSONResult.ok(emp);
    }

    @PutMapping("/add")
    public JSONResult add(@RequestBody Emp emp) {
        int insert = empService.insert(emp);
        if (insert > 0) {
            return JSONResult.ok(emp);
        }
        return JSONResult.fail(null);
    }

    @PostMapping("/update")
    public JSONResult update(@RequestBody Emp emp) {
        int insert = empService.update(emp);
        if (insert > 0) {
            return JSONResult.ok(emp);
        }
        return JSONResult.fail(null);
    }

    @DeleteMapping("/delete/{id}")
    public JSONResult delete(@PathVariable String id) {
        int delete = empService.delete(id);
        if (delete > 0) {
            return JSONResult.ok("删除成功");
        }
        return JSONResult.fail("删除失败");
    }

}
