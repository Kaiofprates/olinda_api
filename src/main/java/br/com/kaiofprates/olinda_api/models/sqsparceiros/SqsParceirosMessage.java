package br.com.kaiofprates.olinda_api.models.sqsparceiros;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SqsParceirosMessage {

    private String url;

    private int page;

    @JsonProperty("page-size")
    private int size;

}
