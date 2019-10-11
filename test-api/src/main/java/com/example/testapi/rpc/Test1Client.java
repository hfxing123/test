package com.example.testapi.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Primary
@FeignClient(name = "test-service",fallback = Test1Client.Test1ClientFallback.class)
public interface Test1Client {


    @RequestMapping(method = RequestMethod.POST, value = "/getData9")
    String iFailSometimes();

    @Component
    class Test1ClientFallback implements Test1Client {
        @Override
        public String iFailSometimes() {

            System.out.println("进入了iFailSometimes");

            return "fallback";
        }
    }


}
