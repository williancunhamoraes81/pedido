package com.example.Pedido.service;

import com.example.Pedido.domain.Pedido;
import com.example.Pedido.domain.dto.PedidoDTO;
import com.example.Pedido.repository.PedidoRepository;
import com.example.Pedido.repository.UtilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UtilRepository utilRepository;

    public ResponseEntity<Object> cadastrarPedido(Pedido pedido) {

        if (pedidoRepository.existsByNumeroControle(pedido.getNumeroControle())) {
            return ResponseEntity.badRequest().body("Número de Controle já cadastrado.");
        }

        return ResponseEntity.ok().body(pedidoRepository.save(validaDadosPedido(pedido)));
    }

    public void popularTabela(){
        try{
            for (int contador = 0; contador < 10; contador++) {
                cadastrarPedido(Pedido.builder()
                                .codigoCliente(1000L)
                                .nomeProduto("Produto ".concat(String.valueOf(contador)))
                                .dataCadastro(LocalDate.now())
                                .quantidade(contador)
                                .valorUnitario(contador * 3)
                                .numeroControle(String.valueOf(contador))
                        .build());
            }
        }catch (Exception e){
            System.out.println("Erro ao popular tabela.");
        }
    }

    private Pedido validaDadosPedido(Pedido pedido){

        if (pedido.getDataCadastro() == null) {
            pedido.setDataCadastro(LocalDate.now());
        }

        if(Objects.isNull(pedido.getQuantidade() <= 0)){
            pedido.setQuantidade(1);
        }

        pedido.setValorTotal(calculaValorTotal(pedido));

        if(Objects.isNull(pedido.getDataCadastro())){
            pedido.setDataCadastro(LocalDate.now());
        }

        return pedido;

    }

    private Double calculaValorTotal(Pedido pedido){
        if (pedido.getQuantidade() > 5 && pedido.getQuantidade() < 10) {
            return (pedido.getValorUnitario() * pedido.getQuantidade() * 0.95);
        } else if (pedido.getQuantidade() >= 10) {
            return (pedido.getValorUnitario() * pedido.getQuantidade() * 0.9);
        } else {
           return (pedido.getValorUnitario() * pedido.getQuantidade());
        }
    }

    public ResponseEntity<Object> consultarPedidos() {
        List<Pedido> list = pedidoRepository.findAll();
        return list.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok().body(list);
    }

    public Optional<Pedido> consultarPedidoPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    public ResponseEntity<Object> excluirPedidoPorId(Long id) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        if(pedidoOptional.isPresent()){
            pedidoRepository.delete(pedidoOptional.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    public Page<PedidoDTO> consultarFiltro(PedidoDTO pedidoDTO, Pageable pageable) {
        Page<Pedido> listDtos = (Page<Pedido>)utilRepository.search(pageable, pedidoDTO);

        return new PageImpl<>(listDtos.stream()
                .map(m -> PedidoDTO.builder().dataCadastro(m.getDataCadastro()).numeroControle(m.getNumeroControle()).build())
                .collect(Collectors.toList()), pageable, listDtos.getTotalElements());
    }
}


