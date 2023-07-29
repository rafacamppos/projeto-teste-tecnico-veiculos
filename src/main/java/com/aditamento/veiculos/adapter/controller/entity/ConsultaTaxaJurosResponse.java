package com.aditamento.veiculos.adapter.controller.entity;

import com.aditamento.veiculos.adapter.controller.dto.JurosCalculadoDTO;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;


@Data
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ConsultaTaxaJurosResponse {
    private JurosCalculadoDTO data;
}
