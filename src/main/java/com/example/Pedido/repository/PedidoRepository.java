package com.example.Pedido.repository;

import com.example.Pedido.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    boolean existsByNumeroControle(String numeroControle);
}

