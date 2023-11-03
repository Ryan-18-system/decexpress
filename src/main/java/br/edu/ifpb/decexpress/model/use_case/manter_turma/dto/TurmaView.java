package br.edu.ifpb.decexpress.model.use_case.manter_turma.dto;

import br.edu.ifpb.decexpress.model.use_case.manter_aluno.dto.AlunoView;
import br.edu.ifpb.decexpress.utils.NivelTurma;

import java.io.Serializable;
import java.util.List;

public record TurmaView(
        Long codTurma,
        Integer serie,
        Integer ano,
        NivelTurma nivel,
        char turma,
        List<AlunoView> listaAlunos
) implements Serializable {
}
