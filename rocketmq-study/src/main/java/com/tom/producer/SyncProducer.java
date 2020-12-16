package com.tom.producer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

public class SyncProducer {

    public static void main(String[] args) {
        DefaultMQProducer mqProducer = new DefaultMQProducer("tom");
        mqProducer.setNamesrvAddr("127.0.0.1:9876");
        try {
            mqProducer.start();
            for (int i = 0; i < 100; i++) {
                Message message = new Message("topicTest","tagA",
                        ("Hello RocketMq"+i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                SendResult result = mqProducer.send(message);
                System.out.println(result);
            }
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            mqProducer.shutdown();
        }

    }
}
