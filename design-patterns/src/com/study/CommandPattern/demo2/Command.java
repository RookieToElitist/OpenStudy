package com.study.CommandPattern.demo2;

/**
 * 命令接口，动作的请求者：所有的命令对象都需要实现这个接口
 */
public interface Command {
    //执行
    public void execute();
    //撤销
    public void undo();
    //记录日志
    public void storeLog();
}
