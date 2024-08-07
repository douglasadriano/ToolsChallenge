package br.com.banco.APIPagamentos.http.controller;

import br.com.banco.APIPagamentos.entity.Descricao;
import br.com.banco.APIPagamentos.entity.Pagamento;
import br.com.banco.APIPagamentos.service.PagamentoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pagamento salvar(@RequestBody Map<String, Object> requestBody) {
        Map<String, Object> transacao = (Map<String, Object>) requestBody.get("transacao");
        Pagamento pagamento = objectMapper.convertValue(transacao, Pagamento.class);

        Descricao descricao = pagamento.getDescricao();
        descricao.setNsu(1234567890L);
        descricao.setCodigoAutorizacao(147258369L);
        descricao.setStatus("AUTORIZADO");

        pagamento.setDescricao(descricao);

        return pagamentoService.salvar(pagamento);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Pagamento> listaPagamento() {
        return pagamentoService.listaPagamento();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Pagamento buscarPagamentoPorID(@PathVariable("id") Long id) {
        return pagamentoService.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pagamento não encontrado."));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerPagamento(@PathVariable("id") Long id) {
        pagamentoService.buscarPorId(id)
                .map(Pagamento -> {
                    pagamentoService.removerPorID(Pagamento.getId());
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pagamento não encontrado."));
    }

    @PutMapping("/estornar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Pagamento estornarPagamento(@PathVariable("id") Long id) {
        return pagamentoService.buscarPorId(id)
                .map(pagamento -> {
                    Descricao descricao = pagamento.getDescricao();
                    descricao.setStatus("CANCELADO");
                    pagamento.setDescricao(descricao);
                    return pagamentoService.salvar(pagamento);
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pagamento não encontrado."));
    }
}
