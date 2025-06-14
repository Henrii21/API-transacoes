package com.transacoes.demo.service.impl;

import com.transacoes.demo.dto.RequisicaoComBancoDTO;
import org.springframework.stereotype.Service;

import java.time.ZoneId;

@Service("banco2Service")
public class Banco2ServiceImpl extends Banco1ServiceImpl {

    @Override
    public void adicionarTransacao(RequisicaoComBancoDTO dto) {
        if (dto.getDataHora().atZone(ZoneId.systemDefault()).getYear() != 2025) return;
        super.adicionarTransacao(dto);
    }
}
