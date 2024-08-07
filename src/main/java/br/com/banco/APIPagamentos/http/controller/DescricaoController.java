package br.com.banco.APIPagamentos.http.controller;

import br.com.banco.APIPagamentos.entity.Descricao;
import br.com.banco.APIPagamentos.service.DescricaoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/descricao")
public class DescricaoController {

    @Autowired
    private DescricaoService descricaoService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Descricao salvar(@RequestBody Descricao descricao) {
        return descricaoService.salvar(descricao);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Descricao> listaDescricao(){
        return descricaoService.listaDescricao();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Descricao buscarDescricaoPorID(@PathVariable("id") Long id) {
        return descricaoService.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Descrição não encontrada."));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerDescricao(@PathVariable("id") Long id){
        descricaoService.buscarPorId(id)
                .map(Pagamento -> {
                    descricaoService.buscarPorId(Pagamento.getId());
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Descrição não encontrada."));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarDescricao(@PathVariable("id") Long id, @RequestBody Descricao descricao){
        descricaoService.buscarPorId(id)
                .map(pagamentoBase ->{
                    modelMapper.map(descricao, pagamentoBase);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Descrição não encontrada."));
    }
}
