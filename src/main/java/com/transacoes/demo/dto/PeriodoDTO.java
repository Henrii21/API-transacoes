package com.transacoes.demo.dto;

import java.time.ZonedDateTime;

public class PeriodoDTO {
    private ZonedDateTime dataInicial;
    private ZonedDateTime dataFinal;

    public ZonedDateTime getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(ZonedDateTime dataInicial) {
        this.dataInicial = dataInicial;
    }

    public ZonedDateTime getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(ZonedDateTime dataFinal) {
        this.dataFinal = dataFinal;
    }
}
