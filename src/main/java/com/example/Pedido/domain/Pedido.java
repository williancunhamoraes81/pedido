package com.example.Pedido.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@Builder
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroControle;
    private LocalDate dataCadastro;
    private String nomeProduto;
    private double valorUnitario;
    private int quantidade;
    private Long codigoCliente;
    private double valorTotal;

}