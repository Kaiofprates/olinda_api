package br.com.kaiofprates.olinda_api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PontosPixResponse {

    @JsonProperty("links")
    private Links links;

    @JsonProperty("meta")
    private Meta meta;

    @JsonProperty("registros")
    private List<AgenteSaque> agenteSaques;

}

