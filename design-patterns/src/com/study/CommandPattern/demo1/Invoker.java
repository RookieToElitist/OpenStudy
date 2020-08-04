package com.study.CommandPattern.demo1;

/**
 * 调用者
 */
public class Invoker  {
    Command command;

    public void setCommand(Command command) {
        this.command = command;
    }


    public void action() {
        command.execute();
    }
}
