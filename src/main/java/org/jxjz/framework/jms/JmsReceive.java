package org.jxjz.framework.jms;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Service;

/**
 * 【功能说明】：JMS 接收处理类
 * @author lihaijun
 * 【创建时间】2017-05-25
 */
@Service
public class JmsReceive  implements MessageListener { 
 
	public void onMessage(Message message) {
		TextMessage tm = (TextMessage) message;
		try {
			System.out.println("ConsumerMessageListener收到了文本消息：\t" + tm.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}
	
}
