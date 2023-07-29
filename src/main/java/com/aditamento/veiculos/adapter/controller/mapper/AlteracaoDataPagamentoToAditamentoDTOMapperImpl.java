package com.aditamento.veiculos.adapter.controller.mapper;

import com.aditamento.veiculos.adapter.controller.dto.AditamentoDTO;
import com.aditamento.veiculos.adapter.controller.dto.ContratoDTO;
import com.aditamento.veiculos.adapter.controller.dto.FinanceiroDTO;
import com.aditamento.veiculos.adapter.controller.entity.AditamentoDataPagamentoEntity;
import com.aditamento.veiculos.adapter.controller.entity.ContratoEntity;
import com.aditamento.veiculos.adapter.controller.entity.FinanceiroEntity;
import com.aditamento.veiculos.adapter.controller.entity.contratos.AlteracaoDataPagamento;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AlteracaoDataPagamentoToAditamentoDTOMapperImpl implements EntityMap<AlteracaoDataPagamento, AditamentoDTO> {

    @Override
    public AditamentoDTO map(AlteracaoDataPagamento alteracaoDataPagamento) {
        ContratoEntity contrato = alteracaoDataPagamento.getContrato();
        AditamentoDataPagamentoEntity aditamentoDataPagamento = alteracaoDataPagamento.getAditamento();

        return AditamentoDTO.builder()
                .novaDataPagamento(aditamentoDataPagamento.getNovaDataPagamento())
                .contratoDTO(ContratoDTO.builder()
                        .ativo(contrato.getAtivo())
                        .dataContratacao(contrato.getDataContratacao())
                        .idContrato(contrato.getIdContrato())
                        .numeroCpfCnpjCliente(contrato.getNumeroCpfCnpjCliente())
                        .parcelasEmAtraso(contrato.getParcelasEmAtraso()).build())
                .financeiroDTO(this.getFinanceiro(alteracaoDataPagamento.getFinanceiro())).build();
    }
    private List<FinanceiroDTO> getFinanceiro(List<FinanceiroEntity> financeiro){
         return financeiro.stream().map(fin->
                 FinanceiroDTO.builder()
                         .dataCalculo(fin.getDataCalculo())
                .diaPagamento(fin.getDiaPagamento())
                .percentualTaxaJuro(fin.getPercentualTaxaJuro())
                .quantidadeParcelas(fin.getQuantidadeParcelas())
                .tipoCalculo(fin.getTipoCalculo())
                .valorParcelas(fin.getValorParcelas())
                .valorTotal(fin.getValorTotal()).build()).collect(Collectors.toList());
    }
}
