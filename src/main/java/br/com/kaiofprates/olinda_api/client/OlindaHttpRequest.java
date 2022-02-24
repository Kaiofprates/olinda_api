package br.com.kaiofprates.olinda_api.client;

import br.com.kaiofprates.olinda_api.models.PontosPixResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "https://api.caixa.gov.br:8443/dadosabertos/pontosPix/v1", name = "olinda")

public interface OlindaHttpRequest {

    @RequestMapping(method = RequestMethod.GET)
    PontosPixResponse getPontos(@RequestParam(value = "page") int page, @RequestParam(value = "page-size") int size);
}
