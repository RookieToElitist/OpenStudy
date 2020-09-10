package com.study.javademo.java.lang.study_enum;


public class Test_Enum_Gender_2 {
    public static void main(String[] args) {

        //错误写法
//        String str="0";
//        switch (str){
//            case Gender.MALE.getType():
//                System.out.println("jake");
//                break;
//            case Gender.FEMALE.getType():
//                System.out.println("rose");
//                break;
//        }

        //正确做法
        switch (Gender.getByValue("10")){
            case MALE:
                System.out.println("jake");
                break;
            case FEMALE:
                System.out.println("rose");
                break;
            default:
                break;
        }

    }
}


