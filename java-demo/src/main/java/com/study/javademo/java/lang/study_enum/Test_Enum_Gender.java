package com.study.javademo.java.lang.study_enum;


public class Test_Enum_Gender {
    public static void main(String[] args) {

        System.out.println("输出父类=="+Gender.class.getSuperclass());

        //toString()
        Gender male = Gender.MALE;
        System.out.println("输出为对象名=="+male);//父类不是Object,所以打印的不是地址
        System.out.println("输出为对象名=="+male.toString());//在重写父类toString方法时，与上面一致

        //values()
        for (Gender gender : Gender.values()) {
            System.out.println(gender);
        }

        //valueOf()
        Gender male1 = Gender.valueOf("MALE");
        System.out.println(male1);



    }
}


