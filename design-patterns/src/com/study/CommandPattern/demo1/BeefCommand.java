package com.study.CommandPattern.demo1;

/**
 * 具体的命令对象，如牛肉类食物命令对象
 */
public class BeefCommand implements Command {

    //需要被命令的对象,所以这里需要有具体接收者或接收者的引用
    private CookerReceiver cookerReceiver;

    /*
    构造器传出牛肉类对象，以便让当前命令对象所控制
     */
    public BeefCommand(CookerReceiver cookerReceiver) {
        this.cookerReceiver = cookerReceiver;
    }

    /*

     */
    @Override
    public void execute() {
        cookerReceiver.cooking();
    }

    @Override
    public void undo() {

    }

    @Override
    public void storeLog() {

    }
}
