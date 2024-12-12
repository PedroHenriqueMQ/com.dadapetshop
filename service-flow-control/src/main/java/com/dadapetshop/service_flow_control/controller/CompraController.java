package com.dadapetshop.service_flow_control.controller;

import com.dadapetshop.service_flow_control.dto.CompraDTO;
import com.dadapetshop.service_flow_control.service.CompraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchase")
public class CompraController {
    @Autowired
    private CompraService compraService;

    @PostMapping("/")
    public ResponseEntity<String> validarCompra(@Valid @RequestBody CompraDTO compraDTO) {
        if (compraService.validarCompra(compraDTO)) {
            return compraService.realizarCompra(compraDTO) ?
                ResponseEntity.ok("Compra realizada com sucesso!") :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Compra não passou na etapa de validação!");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Compra não passou na etapa de validação!");
    }
}
