package com.example.testapi.rpc;

import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//实际上是test-ribbon，只是不能相同的name
//@FeignClient(name = "test-ribbon", fallbackFactory = TestHystrixClient.TestHystrixClientFallbackFactory.class)
//@FeignClient(name = "test-service", fallbackFactory = TestHystrixClient.TestHystrixClientFallbackFactory.class)
public interface TestHystrixClient {

    @RequestMapping(method = RequestMethod.GET, value = "/getData9")
    String iFailSometimes();

    //@Component
    class TestHystrixClientFallbackFactory implements FallbackFactory<TestHystrixClient> {
        @Override
        public TestHystrixClient create(Throwable cause) {
            return new TestHystrixClient() {
                @Override
                public String iFailSometimes() {
                    return "错误的原因为"+"fallback; reason was: " + cause.getMessage();
                }
            };
        }
    }

}
