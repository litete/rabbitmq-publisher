package com.fengqi.rabbitmqpublisher.repository;


import com.fengqi.rabbitmqpublisher.entity.Order;
import com.fengqi.rabbitmqpublisher.entity.OrderExample;

import java.util.List;

public interface OrderMapper {
    long countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Integer id);

    int updateByExampleSelective(Order record, OrderExample example);

    int updateByExample( Order record,  OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}