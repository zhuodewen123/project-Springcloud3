package com.zhuodewen.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@Import(CommonApplication.class)
@PropertySource("classpath:application-good.properties")//配置文件需改名,因为和引入的common模块冲突了
@EnableEurekaClient										//Eureka的消费端(SpringCloud生产者)
@EnableHystrix											//熔断器
@EnableTransactionManagement							//事务管理器
@EnableAsync											//开启Async异步调用(线程)
@EnableJpaRepositories(basePackages={"com.zhuodewen.www.service"})	//开启JPA(Hibernate)
//@MapperScan("com.zhuodewen.www.mapper") 							//mapper扫描器
//@EnableBinding(value= {RabbitMqService.class})					//开启RabbitMQ,注明接口类
public class GoodserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodserverApplication.class, args);
	}

}
