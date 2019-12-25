package com.example.javabasic.juf;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Description:
 * @Author: zhangchao
 * @Date: 2019-11-05 08:30
 **/
@Accessors(chain = true)
@Data
public class Book {
    private String name;
    private String address;
    private String country;

    public static void main(String[] args) {
        Book book = new Book();
        book.setAddress("Beijing").setCountry("China").setName("name");
    }
}
