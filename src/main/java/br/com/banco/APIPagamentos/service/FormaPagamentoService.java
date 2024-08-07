package br.com.banco.APIPagamentos.service;

import br.com.banco.APIPagamentos.entity.FormaPagamento;
import br.com.banco.APIPagamentos.entity.Pagamento;
import br.com.banco.APIPagamentos.repository.FormaPagamentoRepository;
import br.com.banco.APIPagamentos.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormaPagamentoService {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    //serve para salvar ou atualizar, se for novo ele vai salvar, se já existir ele vai atualizar
    public FormaPagamento salvar(FormaPagamento formaPagamento){
        return formaPagamentoRepository.save(formaPagamento);
    }

    public List<FormaPagamento> listaFormaPagamento(){
        return formaPagamentoRepository.findAll();
    }

    public Optional<FormaPagamento> buscarPorId(Long id){
        return formaPagamentoRepository.findById(id);
    }

    public void removerPorID(Long id){
        formaPagamentoRepository.deleteById(id);
    }
}
