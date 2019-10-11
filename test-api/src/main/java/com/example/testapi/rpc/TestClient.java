package com.example.testapi.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@FeignClient(name="test-service")
public interface TestClient {

    @RequestMapping(method = RequestMethod.POST, value = "/getData8")
    String getData1();

}
