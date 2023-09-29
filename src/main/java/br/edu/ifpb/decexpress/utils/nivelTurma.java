package br.edu.ifpb.decexpress.utils;

public enum nivelTurma {
    ENSINO_MEDIO("ENSINO MÉDIO"),
    ENSINO_FUNDAMENTAL_I("ENSINO FUNDAMENTAL I"),
    ENSINO_FUNDAMENTAL_II("ENSINO FUNDAMENTAL II");

    private final String descricao;

    nivelTurma(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
