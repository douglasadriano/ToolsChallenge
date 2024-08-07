package br.com.banco.APIPagamentos.service;

import br.com.banco.APIPagamentos.entity.Descricao;
import br.com.banco.APIPagamentos.repository.DescricaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DescricaoService {

    @Autowired
    private DescricaoRepository descricaoRepository;

    //serve para salvar ou atualizar, se for novo ele vai salvar, se já existir ele vai atualizar
    public Descricao salvar(Descricao descricao){
        return descricaoRepository.save(descricao);
    }

    public List<Descricao> listaDescricao(){
        return descricaoRepository.findAll();
    }

    public Optional<Descricao> buscarPorId(Long id){
        return descricaoRepository.findById(id);
    }

    public void removerPorID(Long id){
        descricaoRepository.deleteById(id);
    }
}
