package com.liming.test.mq;

import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

/**
 * JMS消费者
 * 
 * 
 * <p>
 * 消息题的内容定义
 * <p>
 * 消息对象 接收消息对象后： 接收到的消息体* <p> 
 */
public class MessageConsumer extends ApplicationObjectSupport implements MessageListener{
	
	@Autowired
	private JmsTemplate jmsTemplate;

	public MessageConsumer() {

	}
	

	/**
	 * 监听到消息目的有消息后自动调用onMessage(Message message)方法
	 */
	public void recive() {

		Destination destination = (Destination) this.getApplicationContext()
				.getBean("destination");

		while (true) {

			try {
				TextMessage txtmsg = (TextMessage) jmsTemplate
						.receive(destination);
				if (null != txtmsg) {
					System.out.println("[DB Proxy] " + txtmsg);
					System.out.println("[DB Proxy] 收到消息内容为: "
							+ txtmsg.getText());
				} else
					break;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	public void onMessage(Message message) {
		// XXX Auto-generated method stub
		recive();
	}

}
