package br.com.banco.APIPagamentos.http.controller;

import br.com.banco.APIPagamentos.entity.Descricao;
import br.com.banco.APIPagamentos.entity.Transacao;
import br.com.banco.APIPagamentos.entity.FormaPagamento.Tipo;
import br.com.banco.APIPagamentos.service.TransacaoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Transacao salvar(@RequestBody Map<String, Object> requestBody) {
        try {
            Map<String, Object> transacao = (Map<String, Object>) requestBody.get("transacao");
            Transacao pagamento = objectMapper.convertValue(transacao, Transacao.class);

            // Lógica para definir o status com base no tipo e nas parcelas
            Descricao.Status status;
            if (Tipo.AVISTA.equals(pagamento.getFormaPagamento().getTipo()) && pagamento.getFormaPagamento().getParcelas() > 1) {
                status = Descricao.Status.NEGADO;
            } else {
                status = Descricao.Status.AUTORIZADO;
            }
            pagamento.getDescricao().setStatus(status);

            pagamento.getDescricao().setNsu(1234567890L);
            pagamento.getDescricao().setCodigoAutorizacao(147258369L);

            return transacaoService.salvar(pagamento);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo de pagamento inválido.");
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Transacao> listaTransacao() {
        return transacaoService.listaPagamento();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Transacao buscarPagamentoPorID(@PathVariable("id") Long id) {
        return transacaoService.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transacao não encontrado."));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerPagamento(@PathVariable("id") Long id) {
        transacaoService.buscarPorId(id)
                .map(pagamento -> {
                    transacaoService.removerPorID(pagamento.getId());
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transacao não encontrado."));
    }

    @PutMapping("/estornar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Transacao estornarPagamento(@PathVariable("id") Long id) {
        return transacaoService.buscarPorId(id)
                .map(pagamento -> {
                    pagamento.getDescricao().setStatus(Descricao.Status.CANCELADO);
                    return transacaoService.salvar(pagamento);
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transacao não encontrado."));
    }
}
