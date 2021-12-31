package com.posadskiy.costaccounting.moneyactions.web;

import com.posadskiy.costaccounting.moneyactions.core.MongoConfiguration;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration("web-configuration")
@ComponentScan({"com.posadskiy.costaccounting.moneyactions.core.controller","com.posadskiy.costaccounting.moneyactions.core.validation"})
@Import({com.posadskiy.costaccounting.moneyactions.core.SpringConfiguration.class, MongoConfiguration.class})
public class SpringConfiguration {
	@Bean
	public Queue mainLoginQueue() {
		return new Queue("login");
	}
}
