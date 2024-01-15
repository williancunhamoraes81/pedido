package com.example.Pedido.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PedidoDTO {

    private String numeroControle;
    private LocalDate dataCadastro;

}