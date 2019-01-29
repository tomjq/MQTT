package com.mtqq;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class ServerMqtt {
    public static final String HOST="tcp://127.0.0.1:61613";
    public static final String TOPIC="SDK/JAVA/CLASS";
    public static final String ClientID="server";
    private MqttClient client;
    private MqttTopic topic11;
    private MqttMessage messages;
    private String userName="admin";
    private String passWord="password";
    public ServerMqtt() throws MqttException{
    	client=new MqttClient(HOST,ClientID, new MemoryPersistence());
    	connect();	
    }
	private void connect() {
		MqttConnectOptions options=new MqttConnectOptions();
		options.setCleanSession(false);
        options.setConnectionTimeout(10);
        options.setKeepAliveInterval(20);
        options.setUserName(userName);
        options.setPassword(passWord.toCharArray());
        try {
        	client.setCallback(new pushcallback());
			client.connect(options);
			topic11=client.getTopic(TOPIC);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void publish(MqttTopic topic,MqttMessage message) throws MqttPersistenceException, MqttException{
		MqttDeliveryToken token=topic.publish(message);
		token.waitForCompletion();
		
	}
	public static void main(String[] args) throws MqttPersistenceException, MqttException, InterruptedException {
		ServerMqtt server=new ServerMqtt();
		server.messages=new MqttMessage();
		server.messages.setRetained(true);
		server.messages.setQos(1);
		server.messages.setPayload("abcde".getBytes());
		server.publish(server.topic11, server.messages);
		Thread.sleep(1000);
		server.messages.setPayload("abcdef".getBytes());
		server.publish(server.topic11, server.messages);
		System.out.println(server.topic11+"主题所有内容发送完成");
	}
}
