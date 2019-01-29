package com.mtqq;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class pushcallback implements MqttCallback {

	public void connectionLost(Throwable cause) {
		System.out.println("连接断开可以重连");

	}
	public void deliveryComplete(IMqttDeliveryToken token) {
		System.out.println("发送是否成功："+token.isComplete());
	}
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println("接受消息主题："+topic);
		System.out.println("接受消息Qos："+message.getQos());
		System.out.println("接受消息内容:"+new String(message.getPayload()));
		System.out.println("接受成功");

	}

}
