package br.com.banco.APIPagamentos.service;

import br.com.banco.APIPagamentos.entity.Transacao;
import br.com.banco.APIPagamentos.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagamentoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    public Transacao salvar(Transacao transacao) {
        return transacaoRepository.save(transacao);
    }

    public List<Transacao> listaPagamento() {
        return transacaoRepository.findAll();
    }

    public Optional<Transacao> buscarPorId(Long id) {
        return transacaoRepository.findById(id);
    }

    public void removerPorID(Long id) {
        transacaoRepository.deleteById(id);
    }
}
