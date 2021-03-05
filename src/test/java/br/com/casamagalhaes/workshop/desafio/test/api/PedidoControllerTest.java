package br.com.casamagalhaes.workshop.desafio.test.api;

import br.com.casamagalhaes.workshop.desafio.model.Itens;
import br.com.casamagalhaes.workshop.desafio.model.PedidosDeVenda;
import br.com.casamagalhaes.workshop.desafio.model.Status;
import br.com.casamagalhaes.workshop.desafio.service.PedidosDeVendaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class PedidoControllerTest {

    @Value("${server.port}")
    private int port;

    private RequestSpecification requisicao;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    private void prepararRequisicao() {
        requisicao = new RequestSpecBuilder().setBasePath("/api/v1/pedidos").setPort(port).setAccept(ContentType.JSON)
                .log(LogDetail.ALL).setContentType(ContentType.JSON).build();
    }


    @Test
    public void deveriaReceberMensagemDeOk() {
        given()
                .spec(requisicao)
                .expect()
                .statusCode(HttpStatus.SC_OK)
                .when()
                .get();
    }

    @Test
    public void deveriaCriarUmPedido() throws JsonProcessingException {
        PedidosDeVenda pedidosDeVenda = given()
                .spec(requisicao)
                .body(objectMapper.writeValueAsString(dadoUmPedido()))
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .as(PedidosDeVenda.class);

        assertNotNull(pedidosDeVenda, "pedido não foi cadastrado");
        assertNotNull(pedidosDeVenda.getPedido(), "pedido não gerado");
    }

    @Test
     public void deveriaExcluirUmPedido() throws JsonProcessingException {

        PedidosDeVenda pedidosDeVenda = given()
                .spec(requisicao)
                .body(objectMapper.writeValueAsString(dadoUmPedido()))
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .as(PedidosDeVenda.class);

        assertNotNull(pedidosDeVenda, "pedido não foi cadastrado");
        assertNotNull(pedidosDeVenda.getPedido(), "pedido não gerado");

        given()
                .spec(requisicao)
                .when()
                .delete("/{id}", pedidosDeVenda.getPedido())
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);

    }

    @Test
    public void deveriaMudarStatus () throws JsonProcessingException{
        Status status = new Status();
        status.setStatus("PRONTO");

        PedidosDeVenda pedidosDeVenda = given()
                .spec(requisicao)
                .body(objectMapper.writeValueAsString(dadoUmPedido()))
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .as(PedidosDeVenda.class);

        assertNotNull(pedidosDeVenda, "pedido não foi cadastrado");


        given()
                .spec(requisicao)
                .body(objectMapper.writeValueAsString(status))
                .when()
                .post("/{id}/status", status.getStatus())
                .then()
                .statusCode(HttpStatus.SC_OK);

    }

    @Test
    public void deveriaAlterarUmProduto() throws JsonProcessingException {
        PedidosDeVenda pedidosDeVenda =
                given()
                        .spec(requisicao)
                        .body(objectMapper.writeValueAsString(dadoUmPedido()))
                        .when()
                        .post()
                        .then()
                        .statusCode(HttpStatus.SC_CREATED)
                        .extract()
                        .as(PedidosDeVenda.class);

        assertNotNull(pedidosDeVenda, "produto não foi cadastrado");
        assertNotNull(pedidosDeVenda.getPedido(), "id do produto não gerado");

        pedidosDeVenda.setEndereco("produto alterado");

        PedidosDeVenda produtoaAlterado =
                given()
                        .spec(requisicao)
                        .body(objectMapper.writeValueAsString(pedidosDeVenda))
                        .when()
                        .put("/{id}", pedidosDeVenda.getPedido())
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .extract()
                        .as(PedidosDeVenda.class);

        assertEquals(pedidosDeVenda.getEndereco(), produtoaAlterado.getEndereco(),
                "descrição não foi alterada");
    }



    private PedidosDeVenda dadoUmPedido() {
        int i = 1;
        List<Itens> itens = new ArrayList<Itens>();
        itens.add(new Itens((long) 1, "Batata", 5.0, 3));
        itens.add(new Itens((long) 2, "Macarrao", 5.0, 5));
        itens.add(new Itens((long) 3, "Feijao", 10.0, 1));
        PedidosDeVenda pedidosDeVenda = new PedidosDeVenda((long) i, "Maria Joaquina", "Rua Joaquim Ferraz, 450", "40028922", 5.0, "PENDENTE", itens);
        calcularValorTotal(pedidosDeVenda);
        i++;
        return pedidosDeVenda;
    }

    private void calcularValorTotal(PedidosDeVenda pedidosDeVenda) {
        Double valorTotalProdutos = 0.0;
        for (Itens item : pedidosDeVenda.getItens()) {
            valorTotalProdutos += item.valorTotal();
        }

        pedidosDeVenda.setValorTotalProdutos(valorTotalProdutos);
        pedidosDeVenda.setValorTotal(valorTotalProdutos + pedidosDeVenda.getTaxa());
    }
}


// checkar 400 ao tentar salvar um objeto com parametros errados
// checkar se o somador ta somando corretamente
// receber 400 se ao tentar deletar e atualizar com um id inexistente
// re
