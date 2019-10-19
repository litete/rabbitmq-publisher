package com.fengqi.rabbitmqpublisher.service;


import com.fengqi.rabbitmqpublisher.entity.Order;

/**
 * @Author NieZhiLiang
 * @Email nzlsgg@163.com
 * @Date 2019/1/25 上午9:44
 */
public interface OrderService {

    void createOrder(Order order);

}
