package com.fengqi.rabbitmqpublisher.repository;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fengqi.rabbitmqpublisher.entity.BrokerMessageLog;
import com.fengqi.rabbitmqpublisher.entity.BrokerMessageLogExample;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrokerMessageLogMapper extends BaseMapper<BrokerMessageLog> {
    long countByExample(BrokerMessageLogExample example);

    int deleteByExample(BrokerMessageLogExample example);

    int deleteByPrimaryKey(String message_id);

    int insert(BrokerMessageLog record);

    int insertSelective(BrokerMessageLog record);

    List<BrokerMessageLog> selectByExample(BrokerMessageLogExample example);

    BrokerMessageLog selectByPrimaryKey(String message_id);

    int updateByExampleSelective(BrokerMessageLog record,  BrokerMessageLogExample example);

    int updateByExample(BrokerMessageLog record, BrokerMessageLogExample example);

    int updateByPrimaryKeySelective(BrokerMessageLog record);

    int updateByPrimaryKey(BrokerMessageLog record);
}