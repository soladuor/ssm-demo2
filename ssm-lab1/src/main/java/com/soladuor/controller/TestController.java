package com.soladuor.controller;

import com.soladuor.utils.result.JSONResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@RestController
@RequestMapping("/test")
public class TestController {

    /**
     * 前往testMessage页面
     */
    @GetMapping("/testMessage")
    public ModelAndView testMessage() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("msg", "测试消息");
        mav.setViewName("test/testMessage");
        return mav;
    }

    /**
     * 测试返回对象转JSON
     */
    @GetMapping("/testJsonData")
    @ResponseBody
    public JSONResult testObj() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("a", "aaa");
        map.put("b", 12345);
        return JSONResult.ok(map);
    }

}
