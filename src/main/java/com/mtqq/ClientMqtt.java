package com.mtqq;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class ClientMqtt {
      public static final String HOST="tcp://127.0.0.1:61613";
      public static final String TOPIC="SDK/JAVA/CLASS";
      public static final String ClientID="server";
      private MqttClient client;
      private MqttConnectOptions options;
      private String userName="admin";
      private String passWord="password";
      private void start(){
    	  try {
			client = new MqttClient(HOST, ClientID, new MemoryPersistence());
		} catch (MqttException e1) {
			e1.printStackTrace();
		}
    	  options=new MqttConnectOptions();
  		  options.setCleanSession(true);
          options.setConnectionTimeout(10);
          options.setKeepAliveInterval(20);
          options.setUserName(userName);
          options.setPassword(passWord.toCharArray());
          try {
        	client.setCallback(new pushcallback());  
        	MqttTopic topic = client.getTopic(TOPIC);
  			client.connect(options);
  			String topic1=TOPIC;
  			int Qos=1;
  			
  			client.subscribe(topic1,Qos);
  			} catch( Exception e) {
  			e.printStackTrace();
  		} 
      }
      public static void main(String[] args) {
		ClientMqtt clientmqtt=new ClientMqtt();
		clientmqtt.start();
	}
      
      
}
