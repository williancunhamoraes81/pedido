package com.example.Pedido.controller;

import com.example.Pedido.domain.Pedido;
import com.example.Pedido.domain.dto.PedidoDTO;
import com.example.Pedido.service.PedidoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Object> cadastrarPedido(@RequestBody String requestBody) throws JsonProcessingException {

        ObjectMapper objectMapper;
        if (requestBody.trim().startsWith("<")) {
            // Se começa com "<", assume que é XML
            objectMapper = new XmlMapper();
        } else {
            // Caso contrário, assume que é JSON
            objectMapper = new ObjectMapper();
        }
        Pedido pedido = objectMapper.readValue(requestBody, Pedido.class);
        return pedidoService.cadastrarPedido(pedido);
    }

    @PostMapping("/filter")
    public Page<PedidoDTO> filtrarPedido(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "5") int size,
                              @RequestBody PedidoDTO pedidoDTO) {
        if(pedidoDTO.getNumeroControle() == null && pedidoDTO.getDataCadastro() == null){
            return null;
        }
        return pedidoService.consultarFiltro(pedidoDTO, PageRequest.of(page, size));
    }

    @GetMapping
    public ResponseEntity<Object> consultarPedidos() {
        return pedidoService.consultarPedidos();
    }

    @GetMapping("/{id}")
    public Pedido consultarPedidoPorId(@PathVariable Long id) {
        return pedidoService.consultarPedidoPorId(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirPedidoPorId(@PathVariable Long id) {
        return pedidoService.excluirPedidoPorId(id);
    }
}

