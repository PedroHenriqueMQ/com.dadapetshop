package com.dadapetshop.service_flow_control;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.dadapetshop.service_flow_control.client")
public class ServiceFlowControlApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceFlowControlApplication.class, args);
	}

}
