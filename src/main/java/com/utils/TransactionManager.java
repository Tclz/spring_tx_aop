package com.utils;

/**
 * 和事务管理相关的工具类，包含开启事务、提交事务、回滚事务和释放连接
 */
public class TransactionManager {

    private ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    public void printLog(){
        System.out.println("环绕通知执行");
    }
    //开启事务
    public void beginTransaction(){
        try {
            connectionUtils.getThreadConnection().setAutoCommit(false);
            System.out.println("前置通知，开启事务");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //提交事务
    public void commit(){
        try {
            connectionUtils.getThreadConnection().commit();
            System.out.println("后置通知，提交事务");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    //回滚事务
    public void rollback(){
        try {
            connectionUtils.getThreadConnection().rollback();
            System.out.println("异常通知，回滚事务");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //释放连接
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

}
