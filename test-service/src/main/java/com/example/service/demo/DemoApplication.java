package com.example.service.demo;

import com.example.service.base.DiscardServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

//@ComponentScan(basePackages = {"com.example"})
//@MapperScan("com.example.demo.dao")
//@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private DiscardServer discardServer;

    /*
    @Autowired
    private HotelMapper hotelMapper;
    */



    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    /*
    public DemoApplication(HotelMapper hotelMapper) {
        this.hotelMapper = hotelMapper;
    }
    */


    @Override
    public void run(String... args) throws Exception
    {


        /*
        InetSocketAddress address = new InetSocketAddress(url, port);
        ChannelFuture future = discardServer.start();
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                socketServer.destroy();
            }
        });
        future.channel().closeFuture().syncUninterruptibly();
        */
        //discardServer.start();

    }
}

