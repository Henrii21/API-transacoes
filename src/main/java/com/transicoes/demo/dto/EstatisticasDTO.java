package com.transicoes.demo.dto;

public class EstatisticasDTO {
    private double soma;
    private double media;
    private double min;
    private double max;
    private long quantidade;

    public EstatisticasDTO(double soma, double media, double min, double max, long quantidade) {
        this.soma = soma;
        this.media = media;
        this.min = min;
        this.max = max;
        this.quantidade = quantidade;
    }

    public double getSoma() {
        return soma;
    }

    public double getMedia() {
        return media;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public long getQuantidade() {
        return quantidade;
    }
}
