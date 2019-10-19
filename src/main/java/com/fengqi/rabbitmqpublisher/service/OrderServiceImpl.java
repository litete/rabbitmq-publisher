package com.fengqi.rabbitmqpublisher.service;

import com.alibaba.fastjson.JSONObject;
import com.fengqi.rabbitmqpublisher.constants.Constants;
import com.fengqi.rabbitmqpublisher.entity.BrokerMessageLog;
import com.fengqi.rabbitmqpublisher.entity.Order;
import com.fengqi.rabbitmqpublisher.repository.BrokerMessageLogMapper;
import com.fengqi.rabbitmqpublisher.repository.OrderMapper;
import com.fengqi.rabbitmqpublisher.producer.OrderSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author NieZhiLiang
 * @Email nzlsgg@163.com
 * @Date 2019/1/25 上午9:45
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private BrokerMessageLogMapper brokerMessageLogMapper;

    @Value("${spring.rabbitmq.overtime}")
    private int minutes;

    @Autowired
    private OrderSender orderSender;

    @Override
    @Transactional
    public void createOrder(Order order) {

        //插入订单表
        orderMapper.insertSelective(order);
        //插入rabbitmq投递信息日志表
        BrokerMessageLog brokerMessageLog = new BrokerMessageLog();
        brokerMessageLog.setMessage_id(order.getMessage_id());
        brokerMessageLog.setMessage(JSONObject.toJSONString(order));
        brokerMessageLog.setStatus(Constants.ORDER_SENDING);
        brokerMessageLog.setCreate_time(new Date());
        //下一次投递时间
        brokerMessageLog.setNext_retry(new Date(new Date().getTime() + minutes * 60000));
        brokerMessageLog.setTry_count(0);

        brokerMessageLogMapper.insertSelective(brokerMessageLog);
        try {
            orderSender.send(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
