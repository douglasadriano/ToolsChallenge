package br.com.banco.APIPagamentos.repository;

import br.com.banco.APIPagamentos.entity.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
