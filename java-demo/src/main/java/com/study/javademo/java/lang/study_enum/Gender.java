package com.study.javademo.java.lang.study_enum;

/*

 */
public enum Gender implements Student{
    //逗号分隔，分号结束
    MALE("1","男"){
        @Override
        public void showMyGender() {
            System.out.println("我是男的");
        }
    },
    FEMALE("0","女"){
        @Override
        public void showMyGender() {
            System.out.println("我是女的");
        }
    };

    private final String type;
    private final String desc;

    Gender(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    //提供get
    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public static Gender getByValue(String value){
        for(Gender gender : values()){
            if (value.equals(gender.getType())) {
                return gender;
            }
        }
        return null;//存在空指针异常情况
    }
}
