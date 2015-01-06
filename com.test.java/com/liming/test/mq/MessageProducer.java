package com.liming.test.mq;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.junit.Test;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations={"classpath:ApplicationContext-ActiveMQ.xml"})
public class MessageProducer extends AbstractJUnit4SpringContextTests{
	
	
	@Test
	public void messageProducerTestCase(){
		JmsTemplate template = (JmsTemplate) applicationContext
				.getBean("jmsTemplate");
		
		Destination destination = (Destination) applicationContext
				.getBean("destination");

		template.send(destination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session
						.createTextMessage("发送消息：Hello ActiveMQ Text Message！");
			}
		});
		System.out.println("成功发送了一条JMS消息");

	}

}