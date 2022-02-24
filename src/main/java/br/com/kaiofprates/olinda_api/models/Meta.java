package br.com.kaiofprates.olinda_api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Meta {

    @JsonProperty("total_paginas")
    private Integer totalPaginas;

    @JsonProperty("total_registros")
    private Integer totalRegistros;
}
