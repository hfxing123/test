package com.example.service.controller;

import com.alibaba.fastjson.JSON;
import com.example.service.common.RedisCommon;
import com.example.service.dao.HotelMapper;
import com.example.common.entry.hotel.Hotel;
import com.example.common.entry.tw.Tw;
import com.example.service.dao.TwMapper;
import com.example.service.service.TempService;
import com.example.service.service.TestCVS;
import com.example.service.service.TestService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Api("用户信息管理")
@RestController
public class TestController {

    public final static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private HotelMapper hotelMapper;

    @Autowired
    private TwMapper twMapper;

    @Autowired
    private TestService testService;

    @Autowired
    private TestCVS testCVS;

    @Autowired
    private TempService tempService;

    @Autowired
    private RedisCommon redisCommon;

    @Value("${customValue.tempValue}")
    private String tempValue;

    //@RequestMapping("/getData")
    @PostMapping("/getData")
    public String getData()
    {

        testService.sendData();

        return JSON.toJSON(hotelMapper.selectByCityId(1)).toString();
        //return "testRibbon2";
    }


    //@RequestMapping("/getData2")
    @PostMapping("/getData2")
    public String getData2()
    {
        return testService.getData();
    }

    //@RequestMapping("/getData3")
    @PostMapping("/getData3")
    public String getData3()
    {
        return "获得的tempValue="+tempValue;
    }

    //@RequestMapping("/getData4")
    @PostMapping("/getData4")
    public String getData4()
    {

        //PageHelper.startPage(1, 10);
        List<Hotel> list=hotelMapper.findList(new Hotel());


        return JSON.toJSON(list).toString();

        //return "获得的tempValue="+tempValue;
    }

    //@RequestMapping("/getData5")
    @PostMapping("/getData5")
    public String getData5()
    {

        //pageNum, int pageSize
        PageHelper.startPage(1, 10);
        List<Hotel> list=hotelMapper.findList(new Hotel());
        PageInfo info =new PageInfo(list);

        logger.error("测试错误");

        return JSON.toJSON(info).toString();

        //return "获得的tempValue="+tempValue;
    }


    //@ApiImplicitParam( name = "tImages",value = "请输入对应数据", paramType = "body", required = false, dataType = "TImages")
    @ApiOperation(value = "插入数据",notes = "插入数据")
    @ApiImplicitParam( name = "test",value = "请输入对应数据", paramType = "body", required = false,dataType = "Tw")
    @PostMapping("/getData6")
    public String getData6(Tw tw) throws Exception
    {

        //PageHelper.startPage(1, 10);
        //List<Hotel> list=hotelMapper.findList(new Hotel());

        //testCVS.main(new String[]{});

        testService.insertTw();

        return "成功";

        //return "获得的tempValue="+tempValue;
    }

    @PostMapping("/getData7")
    public String getData7()
    {

        //pageNum, int pageSize
        /*
        PageHelper.startPage(1, 10);
        List<Hotel> list=hotelMapper.findList(new Hotel());
        PageInfo info =new PageInfo(list);

         */

        Tw tw=twMapper.selectByPrimaryKey(1);

        return JSON.toJSON(tw).toString();

        //return "获得的tempValue="+tempValue;
    }

    @PostMapping("/getData8")
    public String getData8()
    {

        Hotel hotel=hotelMapper.selectByCityId(1);

        return JSON.toJSON(hotel).toString();

        //return "获得的tempValue="+tempValue;
    }


    @GetMapping("/getData9")
    public String getData9()
    {

        //Hotel hotel=hotelMapper.selectByCityId(1);

        return "测试";

        //return "获得的tempValue="+tempValue;
    }

    @GetMapping("/getMqData")
    public String getMqData()
    {
        String temp=tempService.getData();
        System.out.println("获得的mq里面的值为====="+temp);
        return temp;
    }


    @GetMapping("/insertTw")
    public String insertTw()
    {
        try
        {
            testCVS.temp(new String[]{});
            return "成功";
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return "失败";
        }
    }

    @GetMapping("/testRedis")
    public String testRedis()
    {
        try
        {
            redisCommon.setData();
            redisCommon.getData();

            return "成功";
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return "失败";
        }
    }

    /*
    @RequestMapping("/")
    public String home()
    {
        return "testRibbon2";
    }

    @RequestMapping("/getData2")
    public  String getData1()
    {

        //可以传递对象过去

        return "testRibbon2的data2,获取到的数据为:   "+restTemplate.getForObject("http://test-ribbon/getData1",String.class);
    }



    @RequestMapping("/getData3")
    public String getData3()
    {

        ServiceInstance serviceInstance = this.loadBalancerClient.choose("test-ribbon");

        for(int i=0;i<10;i++)
        {
            ServiceInstance si = this.loadBalancerClient.choose("test-ribbon");

            System.out.println("第"+i+"次得到的是="+si.getUri()+","+si.getPort());
        }

        return "testRibbon2的data3,获得的数据为"+serviceInstance.getUri();
    }

    @RequestMapping("/getData4")
    public  String getData4()
    {

        Map<String,Object> temp=new HashMap<String,Object>();
        temp.put("temp",123456);

        System.out.println("返回的值为======="+testIntegration.getTest(temp));

        //可以传递对象过去
        return "调用了getData4";

    }

     */

}
