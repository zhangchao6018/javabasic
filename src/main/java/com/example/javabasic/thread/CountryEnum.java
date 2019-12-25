package com.example.javabasic.thread;

import lombok.Getter;

public enum CountryEnum
{
    ONE(1,"齐"),TWO(2,"楚"),THREE(3,"燕"),FOUR(4,"赵"),FIVE(5,"魏"),SIX(6,"韩");

    @Getter private Integer retCode;
    @Getter private String retmessage;

    CountryEnum(Integer retCode, String retmessage) {
        this.retCode = retCode;
        this.retmessage = retmessage;
    }

    public static CountryEnum forEach_CountryEnum(int index){
        CountryEnum[] values = CountryEnum.values();
        for (CountryEnum element : values)
        {
            if (index == element.getRetCode()){
                return element;
            }
        }
        return null;
    }
}
