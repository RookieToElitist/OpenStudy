package com.study.CommandPattern.demo2;



/**
 * 具体的命令对象，如牛肉类食物命令对象
 */
public class LightCommand implements Command {

    //需要被命令的对象,所以这里需要有具体接收者或接收者的引用
    private Receiver receiver;


    public LightCommand(Receiver receiver) {
        this.receiver = receiver;
    }


    @Override
    public void execute() {
        receiver.on();

    }

    @Override
    public void undo() {

    }

    @Override
    public void storeLog() {

    }
}
