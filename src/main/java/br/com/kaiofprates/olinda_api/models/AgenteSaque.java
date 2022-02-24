package br.com.kaiofprates.olinda_api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AgenteSaque {

    @JsonProperty("cnpj_agente_saque")
    private String cnpj;

    @JsonProperty("nome_agente_saque")
    private String nome;
}
