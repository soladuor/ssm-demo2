package com.soladuor.aop;

import com.soladuor.mapper.FileMessageMapper;
import com.soladuor.mapper.LogMapper;
import com.soladuor.pojo.FileMessage;
import com.soladuor.pojo.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

@Component  // 将当前类标识为一个组件
@Aspect     // 将当前类标识为【切面类】【非核心业务提取类】
public class FileLogAop {


    @Autowired
    private LogMapper logMapper;

    @Autowired
    private FileMessageMapper fileMessageMapper;

    // 上传文件记录
    @After("execution( * com.soladuor.service.impl.FileServiceImpl.upload(..) )")
    public void fileUpLog(JoinPoint joinPoint) {
        // 获取参数名
        String methodName = joinPoint.getSignature().getName();
        // 获取参数列表
        Object[] args = joinPoint.getArgs();
        String desc = String.format("调用[%s]方法上传文件, 参数:" + Arrays.toString(args), methodName);
        System.out.println(desc);
        saveLog(desc);
    }

    // 删除文件记录
    @Before("execution( * com.soladuor.service.impl.FileServiceImpl.deleteFile(String) )")
    public void deleteFileLog(JoinPoint joinPoint) {
        // 获取参数名
        String methodName = joinPoint.getSignature().getName();
        // 获取参数列表
        Object[] args = joinPoint.getArgs();
        // 加上文件名
        FileMessage fileMessage = fileMessageMapper.selectByPrimaryKey(String.valueOf(args[0]));
        String fileName = fileMessage.getFileName();
        String desc = String.format("调用[%s]方法删除文件, 文件id:[%s], 文件名:[%s]", methodName, args[0], fileName);
        System.out.println(desc);
        saveLog(desc);
    }

    public void saveLog(String desc) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        Log log = new Log(uuid, new Date(), desc);
        logMapper.insert(log);
    }
}
