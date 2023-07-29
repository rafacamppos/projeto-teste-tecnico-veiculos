package com.aditamento.veiculos.adapter.beans.feign;

import com.aditamento.veiculos.domain.exceptions.ConsultaJurosBadRequestException;
import com.aditamento.veiculos.domain.exceptions.ConsultaJurosGenericErrorException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsultaTaxaJurosErrorDecoder implements ErrorDecoder {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Exception decode(String methodKey, Response response) {

        switch (response.status()){
            case 400:
                logger.error("Status code " + response.status() + ", methodKey = " + methodKey);
                return new ConsultaJurosBadRequestException("Erro ao consultar API de Juros");
            default:
                logger.error("Status code " + response.status() + ", methodKey = " + methodKey);
                return new ConsultaJurosGenericErrorException("Erro genérico ao consultar a API de Juros");
        }
    }
}
