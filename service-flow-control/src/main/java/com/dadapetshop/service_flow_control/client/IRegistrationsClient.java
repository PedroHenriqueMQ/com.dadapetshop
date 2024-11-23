package com.dadapetshop.service_flow_control.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.dadapetshop.service_flow_control.dto.ProdutoDTO;

@FeignClient(name = "registration", url = "http://localhost:8080/registrations/product")
@Component
public interface IRegistrationsClient {
    @GetMapping("/codigo/{codigo}")
    @Bean
    ProdutoDTO getProduto(@PathVariable("codigo") String codigo);
}
