package com.transacoes.demo.dto;

public class EstatisticasDTO {
    private double soma;
    private double media;
    private double max;
    private double min;
    private long quantidade;

    public EstatisticasDTO(double soma, double media, double max, double min, long quantidade) {
        this.soma = soma;
        this.media = media;
        this.max = max;
        this.min = min;
        this.quantidade = quantidade;
    }

    public double getSoma() { return soma; }
    public double getMedia() { return media; }
    public double getMax() { return max; }
    public double getMin() { return min; }
    public long getQuantidade() { return quantidade; }
}
