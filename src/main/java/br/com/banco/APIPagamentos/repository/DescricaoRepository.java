package br.com.banco.APIPagamentos.repository;

import br.com.banco.APIPagamentos.entity.Descricao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DescricaoRepository extends JpaRepository<Descricao, Long> {
}
