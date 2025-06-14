package com.transacoes.demo.dto;

public class EstatisticasDTO {

    private double soma;
    private double media;
    private double max;
    private double min;
    private long count;

    public EstatisticasDTO() {}

    public EstatisticasDTO(double soma, double media, double max, double min, long count) {
        this.soma = soma;
        this.media = media;
        this.max = max;
        this.min = min;
        this.count = count;
    }

    public double getSoma() {
        return soma;
    }

    public void setSoma(double soma) {
        this.soma = soma;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
