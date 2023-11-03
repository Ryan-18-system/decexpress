package br.edu.ifpb.decexpress.model.use_case.manter_turma.dto;

import br.edu.ifpb.decexpress.utils.NivelTurma;

import java.io.Serializable;

public record TurmaForm (
        Integer serie,
        Integer ano,
        NivelTurma nivel,
        char turma
)implements Serializable {}