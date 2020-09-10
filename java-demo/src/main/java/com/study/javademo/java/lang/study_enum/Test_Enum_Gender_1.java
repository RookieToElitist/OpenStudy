package com.study.javademo.java.lang.study_enum;

import static com.study.javademo.java.lang.study_enum.Gender1.FEMALE;
import static com.study.javademo.java.lang.study_enum.Gender1.MALE;


public class Test_Enum_Gender_1 {
    public static void main(String[] args) {

        //可以是枚举类对象
        switch (Gender1.valueOf("FEMALE")){
            case MALE:
                System.out.println("jake");
                break;
            case FEMALE:
                System.out.println("rose");
                break;
        }

    }
}


