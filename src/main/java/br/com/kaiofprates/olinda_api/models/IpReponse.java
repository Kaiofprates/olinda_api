package br.com.kaiofprates.olinda_api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class IpReponse {

    @JsonProperty("ip")
    private String ip;

}
