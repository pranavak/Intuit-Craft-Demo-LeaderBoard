package com.intuitcraft.leaderboard.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.intuitcraft.leaderboard.constants.constants;

@Configuration
public class kafkaConfig {
	@Bean
	public NewTopic topic() {
		return new NewTopic(constants.KAFKA_TOPIC, 1, (short) 1);
	}
}
