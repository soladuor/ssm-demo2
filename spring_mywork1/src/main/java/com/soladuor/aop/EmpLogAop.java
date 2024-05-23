package com.soladuor.aop;

import com.soladuor.mapper.EmpMapper;
import com.soladuor.mapper.LogMapper;
import com.soladuor.pojo.Emp;
import com.soladuor.pojo.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component  // 将当前类标识为一个组件
@Aspect     // 将当前类标识为【切面类】【非核心业务提取类】
public class EmpLogAop {

    @Autowired
    private LogMapper logMapper;

    @Autowired
    private EmpMapper empMapper;

    /* Aop 无法绑定 static 方法
    为什么：
    因为spring的aop实现原理是通过JDK或者cglib为目标接口或者目标类生成一个代理对象，
    在这个生成的代理对象中，对要切入的方法进行重写，以此来实现各种想要的切面业务操作。

    那为什么aop无法切入static修饰的静态方法/无法代理static方法呢？
    因为static修饰的方法是静态方法，而静态方法是可继承但不可被重写的。
     */

    // execution( public double com.github.aop.CalcImpl.add(double, double) )
    // execution( * *.*(..) ) // 我直呼：真抽象
    // execution( * com.github.aop.*.*(..) ) // aop包下的所有（不包括字包）
    // execution( * com.github.aop.*..*(..) ) // aop包下的所有（包括子包的所有）
    // 重用切入点表达式
    @Pointcut("execution( * com.soladuor.service.impl.EmpServiceImpl.*(..) )")
    public void empPointCut() {
    }

    // 新增 emp 切入点
    @Pointcut("execution( * com.soladuor.service.impl.EmpServiceImpl.insert(com.soladuor.pojo.Emp) )")
    public void empInsertPointCut() {
    }

    // 新增 emp 日志记录
    @Before("empInsertPointCut()")
    public void empInsertLog(JoinPoint joinPoint) {
        // 获取方法签名【方法名+参数列表】：joinPoint.getSignature()
        // 例如：double com.github.aop.Calc.add(double,double)
        // 获取参数名
        String methodName = joinPoint.getSignature().getName();
        // 获取参数列表
        Object[] args = joinPoint.getArgs();
        Emp emp = (Emp) args[0];
        String desc = String.format("调用[%s]方法新增用户emp_id=[%s], e_name=[%s]", methodName, emp.getEmpId(), emp.getEName());
        System.out.println(desc);
        saveLog(desc);
    }

    // 修改 emp 切入点
    @Pointcut("execution( * com.soladuor.service.impl.EmpServiceImpl.update(..) )")
    public void empUpdatePointCut() {
    }

    // 修改 emp 日志记录
    @Around("empUpdatePointCut()")
    public Object empUpdateLog(ProceedingJoinPoint pjp) {
        Object rs = null;
        // 获取参数名
        String methodName = pjp.getSignature().getName();
        Object[] args = pjp.getArgs();
        Emp newEmp = (Emp) args[0];
        try {
            // 1. 前置通知
            Emp preEmp = empMapper.selectById(newEmp.getEmpId());
            rs = pjp.proceed(); // 触发目标对象的目标方法
            // 2. 返回通知
            String desc = String.format(
                "调用[%s]方法修改用户emp_id=[%s], e_name修改前=[%s], e_name修改后=[%s]",
                methodName, preEmp.getEmpId(), preEmp.getEName(), newEmp.getEName()
            );
            System.out.println(desc);
            saveLog(desc);
        } catch (Throwable e) {
            // throw new RuntimeException(e);
            e.printStackTrace();
            // 3. 异常通知
            System.out.println("==> around-> 调用EmpServiceImpl中" + methodName + "方法，发生异常执行，异常：" + e);
        } finally {
            // 4. 后置通知
            System.out.println("==> around-> 调用EmpServiceImpl中" + methodName + "方法之后执行。。。");
        }
        return rs;
    }

    // 删除 emp 切入点
    @Pointcut("execution( * com.soladuor.service.impl.EmpServiceImpl.delete(..) )")
    public void empDeletePointCut() {
    }

    // 删除 emp 日志记录
    @Before("empDeletePointCut()")
    public void empDeleteLog(JoinPoint joinPoint) {
        // 获取参数名
        String methodName = joinPoint.getSignature().getName();
        // 获取参数列表
        Object[] args = joinPoint.getArgs();
        String id = (String) args[0];
        String desc = String.format("调用[%s]方法删除用户emp_id=[%s]", methodName, id);
        System.out.println(desc);
        saveLog(desc);
    }

    public void saveLog(String desc) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        Log log = new Log(uuid, new Date(), desc);
        logMapper.insert(log);
    }

}
