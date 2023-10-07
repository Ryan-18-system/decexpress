package br.edu.ifpb.decexpress.utils;

import lombok.Getter;

@Getter
public enum NivelTurma {
    ENSINO_MEDIO("ENSINO MÉDIO"),
    ENSINO_FUNDAMENTAL_I("ENSINO FUNDAMENTAL I"),
    ENSINO_FUNDAMENTAL_II("ENSINO FUNDAMENTAL II");

    private final String descricao;

    NivelTurma(String descricao) {
        this.descricao = descricao;
    }

}
