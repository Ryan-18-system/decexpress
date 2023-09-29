package br.edu.ifpb.decexpress.model.use_case.manter_turma.dto;

import br.edu.ifpb.decexpress.model.use_case.manter_aluno.dto.AlunoView;
import br.edu.ifpb.decexpress.utils.nivelTurma;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public record TurmaView(
        Long codTurma,
        Integer serie,
        Integer ano,
        nivelTurma nivel,
        char turma,
        List<AlunoView> listaAlunos
) {
}
