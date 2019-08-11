package com.example.demo.base;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

public class TestService {

    /*
    @Autowired
    CityMapper cityMapper;
    */

    public String getMessage(String msg)
    {

        System.out.println("进入了getMessage");

        //cityMapper.findByState("1");

        System.out.println(msg);


        return msg+new Random().nextInt();


    }


}
