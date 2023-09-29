package br.edu.ifpb.decexpress.model.use_case.manter_turma.dto;

import br.edu.ifpb.decexpress.utils.nivelTurma;

public record TurmaForm (
        Integer serie,
        Integer ano,
        nivelTurma nivel,
        char turma
){}