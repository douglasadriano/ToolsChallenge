package br.com.banco.APIPagamentos;

import br.com.banco.APIPagamentos.entity.Transacao;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class APIPagamentosTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private Map<String, Object> pagamentoRequest;
	private Long savedPagamentoId;

	@BeforeEach
	void setUp() {
		pagamentoRequest = new HashMap<>();
		Map<String, Object> transacao = new HashMap<>();

		Map<String, Object> descricao = new HashMap<>();
		descricao.put("valor", 500.50);
		descricao.put("dataHora", "01/05/2021 18:30:00");
		descricao.put("estabelecimento", "PetShop Mundo Cão");

		Map<String, Object> formaPagamento = new HashMap<>();
		formaPagamento.put("tipo", "AVISTA");
		formaPagamento.put("parcelas", 1);

		transacao.put("cartao", "4444********1234");
		transacao.put("descricao", descricao);
		transacao.put("formaPagamento", formaPagamento);

		pagamentoRequest.put("transacao", transacao);
	}

	@Test
	void testSalvarPagamento() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/transacao")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(pagamentoRequest)))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.descricao.status").value(anyOf(equalTo("AUTORIZADO"), equalTo("NEGADO"))))
				.andDo(print())
				.andReturn();

		String responseContent = result.getResponse().getContentAsString();
		Transacao savedTransacao = objectMapper.readValue(responseContent, Transacao.class);
		savedPagamentoId = savedTransacao.getId();
	}

	@Test
	void testListarPagamentos() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/transacao")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(print());
	}

	@Test
	void testBuscarPagamentoPorID() throws Exception {
		// Primeiro, salve um pagamento para garantir que ele existe no banco de dados
		testSalvarPagamento();

		mockMvc.perform(MockMvcRequestBuilders.get("/transacao/" + savedPagamentoId)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.descricao.status").value(anyOf(equalTo("AUTORIZADO"), equalTo("NEGADO"))))
				.andDo(print());
	}

	@Test
	void testRemoverPagamento() throws Exception {
		// Primeiro, salve um pagamento para garantir que ele existe no banco de dados
		testSalvarPagamento();

		mockMvc.perform(MockMvcRequestBuilders.delete("/transacao/" + savedPagamentoId)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNoContent())
				.andDo(print());
	}

	@Test
	void testEstornarPagamento() throws Exception {
		// Primeiro, salve um pagamento para garantir que ele existe no banco de dados
		testSalvarPagamento();

		mockMvc.perform(MockMvcRequestBuilders.put("/transacao/estornar/" + savedPagamentoId)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.descricao.status").value("CANCELADO"))
				.andDo(print());
	}

	@Test
	void testSalvarPagamentoComTipoFormaPagamentoValido() throws Exception {
		String[] tiposValidos = {"AVISTA", "PARCELADO LOJA", "PARCELADO EMISSOR"};
		for (String tipo : tiposValidos) {
			pagamentoRequest = new HashMap<>();
			Map<String, Object> transacao = new HashMap<>();

			Map<String, Object> descricao = new HashMap<>();
			descricao.put("valor", 500.50);
			descricao.put("dataHora", "01/05/2021 18:30:00");
			descricao.put("estabelecimento", "PetShop Mundo Cão");

			Map<String, Object> formaPagamento = new HashMap<>();
			formaPagamento.put("tipo", tipo);
			formaPagamento.put("parcelas", 1);

			transacao.put("cartao", "4444********1234");
			transacao.put("descricao", descricao);
			transacao.put("formaPagamento", formaPagamento);

			pagamentoRequest.put("transacao", transacao);

			mockMvc.perform(MockMvcRequestBuilders.post("/transacao")
							.contentType(MediaType.APPLICATION_JSON)
							.content(objectMapper.writeValueAsString(pagamentoRequest)))
					.andExpect(MockMvcResultMatchers.status().isCreated())
					.andExpect(MockMvcResultMatchers.jsonPath("$.formaPagamento.tipo").value(tipo))
					.andDo(print());
		}
	}

	@Test
	void testSalvarPagamentoComTipoFormaPagamentoInvalido() throws Exception {
		String[] tiposInvalidos = {"DEBITO", "CREDITO"};
		for (String tipo : tiposInvalidos) {
			pagamentoRequest = new HashMap<>();
			Map<String, Object> transacao = new HashMap<>();

			Map<String, Object> descricao = new HashMap<>();
			descricao.put("valor", 500.50);
			descricao.put("dataHora", "01/05/2021 18:30:00");
			descricao.put("estabelecimento", "PetShop Mundo Cão");

			Map<String, Object> formaPagamento = new HashMap<>();
			formaPagamento.put("tipo", tipo);
			formaPagamento.put("parcelas", 1);

			transacao.put("cartao", "4444********1234");
			transacao.put("descricao", descricao);
			transacao.put("formaPagamento", formaPagamento);

			pagamentoRequest.put("transacao", transacao);

			mockMvc.perform(MockMvcRequestBuilders.post("/transacao")
							.contentType(MediaType.APPLICATION_JSON)
							.content(objectMapper.writeValueAsString(pagamentoRequest)))
					.andExpect(MockMvcResultMatchers.status().isBadRequest())
					.andExpect(MockMvcResultMatchers.status().reason(containsString("Tipo de pagamento inválido")))
					.andDo(print());
		}
	}
}
