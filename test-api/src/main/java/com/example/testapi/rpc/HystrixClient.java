package com.example.testapi.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//实际上是test-ribbon，只是不能相同的name
//@FeignClient(name = "test-ribbon",fallback = HystrixClient.HystrixClientFallback.class)
@Primary
@FeignClient(name = "test-service",fallback = HystrixClient.HystrixClientFallback.class)
public interface HystrixClient {


    @RequestMapping(method = RequestMethod.POST, value = "/getData7")
    String iFailSometimes();

    @Component
    class HystrixClientFallback implements HystrixClient {
        @Override
        public String iFailSometimes() {

            System.out.println("进入了iFailSometimes");

            return "fallback";
        }
    }


}
