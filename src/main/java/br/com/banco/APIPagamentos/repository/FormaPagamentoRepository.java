package br.com.banco.APIPagamentos.repository;

import br.com.banco.APIPagamentos.entity.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {
}
