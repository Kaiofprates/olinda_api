package br.com.kaiofprates.olinda_api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Links {

    @JsonProperty("next")
    private String next;

    @JsonProperty("last")
    private String last;
}
