package com.aditamento.veiculos.aplication.usecases;

import com.aditamento.veiculos.adapter.datastore.entity.Aditamento;

import com.aditamento.veiculos.adapter.datastore.mapper.AlteraDataPagamentoToAditamentoMapperImpl;
import com.aditamento.veiculos.domain.entity.AlteraDataPagamento;
import com.aditamento.veiculos.domain.exceptions.BusinessException;
import com.aditamento.veiculos.mocks.AlteraDataPagamentoMock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AlteraDataPagamentoUseCaseImplTest {

    @Spy
    @InjectMocks
    private AlteraDataPagamentoUseCaseImpl dataPagamentoUseCase;

    @Mock
    private AlteraDataPagamentoToAditamentoMapperImpl mapper ;


    @Test
    public void alteraDataPagamentoTest()
    {
        AlteraDataPagamento alteraDataPagamento = AlteraDataPagamentoMock.getAlteraDataPagamento();
        Aditamento aditamentoAlteraData = AlteraDataPagamentoMock.getAditamentoAlteradaDataPagamento();
        lenient().when(dataPagamentoUseCase.alteraDataPagamento(AlteraDataPagamentoMock.getAlteraDataPagamento())).thenReturn(aditamentoAlteraData);
    }

    @Test
    public void erroContratoInativo() {
        AlteraDataPagamento alteraDataPagamento = AlteraDataPagamentoMock.getAlteraDataPagamento();
        alteraDataPagamento.setAtivo(false);
        BusinessException businessException = assertThrows(BusinessException.class, () -> dataPagamentoUseCase.alteraDataPagamento(alteraDataPagamento));
        assertTrue(businessException.getMessage().contains("Não é possivel alterar data de pagamento, contrato inativo."));
    }

    @Test
    public void erroDataPagamento() {
        AlteraDataPagamento alteraDataPagamento = AlteraDataPagamentoMock.getAlteraDataPagamento();
        alteraDataPagamento.setNovaDataPagamento(12);
        BusinessException businessException = assertThrows(BusinessException.class, () -> dataPagamentoUseCase.alteraDataPagamento(alteraDataPagamento));
        assertTrue(businessException.getMessage().contains("Não é possivel alterar data de pagamento, limite máximo para alteração é"));
    }
}
