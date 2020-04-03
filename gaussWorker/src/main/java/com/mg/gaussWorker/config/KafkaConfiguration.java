package com.mg.gaussWorker.config;

import java.util.HashMap;

import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.mg.gaussWorker.model.Event;
import com.mg.gaussWorker.model.RequestData;

/**Class to setup Kafka Factory object with required attributes, 
 * Construct Kafkatemplate object to initiate send request from Zookeeper 
 * */

@EnableKafka
@Configuration
public class KafkaConfiguration {
	
	@Value("${kafka.kafkaIpPort}")
	private String kafkaIpPort;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	 @Bean
	    public ConsumerFactory<String, String> consumerFactory() {
	        Map<String, Object> config = new HashMap<>();

	        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaIpPort);
	        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
	        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	        
	        logger.info("Kafka factory object initiated");

	        return new DefaultKafkaConsumerFactory<>(config);
	    }


	    @Bean
	    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
	        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory();
	        factory.setConsumerFactory(consumerFactory());
	        return factory;
	    }


	    @Bean
	    public ConsumerFactory<String, Event> userConsumerFactory() {
	        Map<String, Object> config = new HashMap<>();

	        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaIpPort);
	        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_json");
	        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
	        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
	        config.put(JsonDeserializer.REMOVE_TYPE_INFO_HEADERS,"true");
	        
	        logger.info("Kafka factory object initiated with event object");
	        
	        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
	                new JsonDeserializer<>(Event.class));
	    }

	    @Bean
	    public ConcurrentKafkaListenerContainerFactory<String, Event> userKafkaListenerFactory() {
	        ConcurrentKafkaListenerContainerFactory<String, Event> factory = new ConcurrentKafkaListenerContainerFactory<>();
	        factory.setConsumerFactory(userConsumerFactory());
	        return factory;
	    }
	    
	    @Bean
	    public ConsumerFactory<String, RequestData> requestDataConsumerFactory() {
	        Map<String, Object> config = new HashMap<>();

	        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaIpPort);
	        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_json");
	        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
	        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
	        config.put(JsonDeserializer.REMOVE_TYPE_INFO_HEADERS,"true");
	        
	        logger.info("Kafka factory object initiated");
	        
	        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
	                new JsonDeserializer<>(RequestData.class));
	    }

	    @Bean
	    public ConcurrentKafkaListenerContainerFactory<String, RequestData> requestDataKafkaListenerFactory() {
	        ConcurrentKafkaListenerContainerFactory<String, RequestData> factory = new ConcurrentKafkaListenerContainerFactory<>();
	        factory.setConsumerFactory(requestDataConsumerFactory());
	        return factory;
	    }
}
