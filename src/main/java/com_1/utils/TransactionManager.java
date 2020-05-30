package com_1.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 和事务管理相关的工具类，包含开启事务、提交事务、回滚事务和释放连接
 */
@Component
@Aspect
public class TransactionManager {

    @Autowired
    private ConnectionUtils connectionUtils;

    @Pointcut("execution(* *..AccountServiceImpl.transfer(..))")
    private void pt1(){}
    //开启事务
    //@Before("pt1()")
    public void beginTransaction(){
        try {
            connectionUtils.getThreadConnection().setAutoCommit(false);
            System.out.println("前置通知，开启事务");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //提交事务
    //@AfterReturning("pt1()")
    public void commit(){
        try {
            connectionUtils.getThreadConnection().commit();
            System.out.println("后置通知，提交事务");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    //回滚事务
    //@AfterThrowing("pt1()")
    public void rollback(){
        try {
            connectionUtils.getThreadConnection().rollback();
            System.out.println("异常通知，回滚事务");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //释放连接
    //@After("pt1()")
    public void release(){
        try {
            // 把连接关闭，还回连接池中
            connectionUtils.getThreadConnection().close();
            // 进行解绑
            connectionUtils.removeConnection();
            System.out.println("最终通知，释放连接");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Around("pt1()")
    public Object aroundAdvice(ProceedingJoinPoint pjp){
        Object value = null;
        try {
            Object[] args = pjp.getArgs();
            this.beginTransaction();
            value = pjp.proceed(args);
            this.commit();
            return value;
        }catch (Throwable t){
            this.rollback();
            throw new RuntimeException(t);
        }finally {
            this.release();
        }
    }
}
