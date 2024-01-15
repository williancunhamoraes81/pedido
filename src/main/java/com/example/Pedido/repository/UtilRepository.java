package com.example.Pedido.repository;

import com.example.Pedido.domain.Pedido;
import com.example.Pedido.domain.dto.PedidoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UtilRepository {

    private static final String VARIABLE_SQL_SELECT = " SELECT ";
    private static final String VARIABLE_SQL_COUNT = " COUNT(*) ";
    private static final String VARIABLE_SQL_FROM = " FROM ";
    private static final String VARIABLE_SQL_WHERE = " WHERE ";
    private static final String VARIABLE_SQL_CONDITION_AND = " AND ";
    private static final String VARIABLE_SQL_TABLE = "pedido";

    @PersistenceContext
    private EntityManager entityManager;


    public <T> Page<T> createPage(List<T> resultList, Pageable pageable, String where, Class<?> clazz) {
        return new PageImpl(resultList,pageable,executeCountQuery(where, clazz));
    }

    public Page<Pedido> search(Pageable pageable, PedidoDTO pedidoDTO) {
        String where = " 1=1 ";
        if(pedidoDTO.getNumeroControle() != null){
            where = where + VARIABLE_SQL_CONDITION_AND + " numero_controle = '" + pedidoDTO.getNumeroControle() + "' ";
        }

        if(pedidoDTO.getDataCadastro() != null){
            where = where + VARIABLE_SQL_CONDITION_AND + " data_cadastro = '" + pedidoDTO.getDataCadastro() + "' ";;
        }

        String queryDados = VARIABLE_SQL_SELECT.
                concat("*").
                concat(VARIABLE_SQL_FROM).
                concat(VARIABLE_SQL_TABLE.
                        concat(VARIABLE_SQL_WHERE).
                        concat(where));

        Query q = entityManager.createNativeQuery(queryDados, Pedido.class);
        q.setMaxResults(pageable.getPageSize());
        q.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        return createPage(q.getResultList(), pageable, where, Pedido.class);
    }

    private Long executeCountQuery(String queryWhereConditions, Class<?> clazz){
        String queryCount = VARIABLE_SQL_SELECT.
                concat(VARIABLE_SQL_COUNT).
                concat(VARIABLE_SQL_FROM).
                concat(VARIABLE_SQL_TABLE.
                        concat(VARIABLE_SQL_WHERE).
                        concat(queryWhereConditions));

        Query typedQueryCount = entityManager.createNativeQuery(queryCount);
        return Long.parseLong(String.valueOf(typedQueryCount.getSingleResult()));
    }

}
