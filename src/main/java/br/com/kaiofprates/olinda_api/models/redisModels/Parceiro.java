package br.com.kaiofprates.olinda_api.models.redisModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Parceiro {

    private String cnpj;
    private String nome;
}
