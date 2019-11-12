package com.example.service.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * rabbitMq 配置类
 * @author
 * Created on 2019年9月19日13:38:30
 */
@Configuration
public class RabbitConfig {

    /*
    @Resource
    private RabbitTemplate rabbitTemplate;
     */

    Logger log = LoggerFactory.getLogger(RabbitTemplate.class);

//    /**
//     * 定义一个hello的队列
//     * Queue 可以有4个参数
//     *      1.队列名
//     *      2.durable       持久化消息队列 ,rabbitmq重启的时候不需要创建新的队列 默认true
//     *      3.auto-delete   表示消息队列没有在使用时将被自动删除 默认是false
//     *      4.exclusive     表示该消息队列是否只在当前connection生效,默认是false
//     */
//    @Bean
//    public Queue helloQueue() {
//        return new Queue("hello-queue");
//    }

    /** ======================== 定制一些处理策略 =============================*/

    /**
     * 定制化amqp模版
     *
     * ConfirmCallback接口用于实现消息发送到RabbitMQ交换器后接收ack回调   即消息发送到exchange  ack
     * ReturnCallback接口用于实现消息发送到RabbitMQ 交换器，但无相应队列与交换器绑定时的回调  即消息发送不到任何一个队列中  ack
     */
    /*
    @Bean //RabbitTemplate getTemplate(ConnectionFactory connectionFactory) rabbitTemplate
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);

        log.debug("创建的连接=================="+rabbitTemplate);

        // 消息确认, yml需要配置 publisher-confirms: true
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {

            log.debug("进入了setConfirmCallback==============");

            if (ack) {
                log.debug("消息发送到exchange成功,id: {}", correlationData!=null?correlationData.getId():null);
            } else {
                log.debug("消息发送到exchange失败,原因: {}", cause);
            }
        });

        // 消息发送失败返回到队列中, yml需要配置 publisher-returns: true
        rabbitTemplate.setMandatory(true);
        // 消息返回, yml需要配置 publisher-returns: true
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {

            log.debug("进入了setReturnCallback");

            //String correlationId = message.getMessageProperties().getCorrelationIdString();
            String correlationId =message.getMessageProperties().getCorrelationId();

            log.debug("消息：{} 发送失败, 应答码：{} 原因：{} 交换机: {}  路由键: {}", correlationId, replyCode, replyText, exchange, routingKey);
        });

        return rabbitTemplate;
    }
    */

//    /**
//     * 配置启用rabbitmq事务
//     * @param connectionFactory
//     * @return
//     */
//    @Bean
//    public RabbitTransactionManager rabbitTransactionManager(CachingConnectionFactory connectionFactory) {
//        return new RabbitTransactionManager(connectionFactory);
//    }

}
