package br.com.banco.APIPagamentos.http.controller;

import br.com.banco.APIPagamentos.entity.FormaPagamento;
import br.com.banco.APIPagamentos.entity.Pagamento;
import br.com.banco.APIPagamentos.service.FormaPagamentoService;
import br.com.banco.APIPagamentos.service.PagamentoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/formapagamento")
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamento salvar(@RequestBody FormaPagamento formaPagamento) {
        return formaPagamentoService.salvar(formaPagamento);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FormaPagamento> listaFormaPagamento(){
        return formaPagamentoService.listaFormaPagamento();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FormaPagamento buscarFormaPagamentoPorID(@PathVariable("id") Long id) {
        return formaPagamentoService.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Forma de Pagamento não encontrada."));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerFormaPagamento(@PathVariable("id") Long id){
        formaPagamentoService.buscarPorId(id)
                .map(FormaPagamento -> {
                    formaPagamentoService.buscarPorId(FormaPagamento.getId());
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Forma de Pagamento não encontrado."));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarFormaPagamento(@PathVariable("id") Long id, @RequestBody FormaPagamento formaPagamento){
        formaPagamentoService.buscarPorId(id)
                .map(formaPagamentoBase ->{
                    modelMapper.map(formaPagamento, formaPagamentoBase);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Forma de Pagamento não encontrado."));
    }
}
