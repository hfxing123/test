package com.example.service.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class TestService {


    Logger log = LoggerFactory.getLogger(TestService.class);

    /*
    @Autowired
    CityMapper cityMapper;
    */

    public String getMessage(String msg)
    {

        System.out.println();

        log.debug("进入了getMessage");

        //cityMapper.findByState("1");

        System.out.println(msg);


        return msg+new Random().nextInt();


    }


}
