package it.lavori.gestione_ruoli.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

	@Bean
	public NewTopic helloworldTopic() {
		//e' possibile creare un partitioning con .name().partitions(n)
		return TopicBuilder.name("helloworld").build();
	}
	
	@Bean
	public NewTopic helloworldJsonTopic() {
		return TopicBuilder.name("helloworld_json").build();
	}
}
