package com.mg.gateWay.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.mg.gateWay.model.RequestData;

import java.util.HashMap;
import java.util.Map;

/*Class to setup Kafka Factory object with required attributes, 
 * Construct Kafkatemplate object to initiate send request to Zookeeper 
 * */

@Configuration
public class KakfaConfiguration {

	@Value("${kafka.kafkaIpPort}")
	private String kafkaIpPort;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Bean
	public ProducerFactory<String, String> producerFactory() {
		Map<String, Object> config = new HashMap<>();

		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaIpPort);
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

		logger.debug("Kafka factory object initiated");

		return new DefaultKafkaProducerFactory<>(config);
	}

	@Bean
	public ProducerFactory<String, RequestData> producerFactoryRequestData() {
		Map<String, Object> config = new HashMap<>();

		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaIpPort);
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		config.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, "false");

		logger.debug("Kafka factory object initiated");

		return new DefaultKafkaProducerFactory<>(config);
	}

	@Bean
	public KafkaTemplate<String, String> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}

	@Bean
	public KafkaTemplate<String, RequestData> kafkaRequestDataTemplate() {
		return new KafkaTemplate<>(producerFactoryRequestData());
	}

}