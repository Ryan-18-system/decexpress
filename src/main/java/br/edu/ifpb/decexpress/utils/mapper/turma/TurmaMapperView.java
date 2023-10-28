package br.edu.ifpb.decexpress.utils.mapper.turma;

import br.edu.ifpb.decexpress.model.entity.Aluno;
import br.edu.ifpb.decexpress.model.entity.Turma;
import br.edu.ifpb.decexpress.model.use_case.manter_aluno.repository.AlunoRepository;
import br.edu.ifpb.decexpress.model.use_case.manter_aluno.service.AlunoService;
import br.edu.ifpb.decexpress.model.use_case.manter_turma.dto.TurmaView;
import br.edu.ifpb.decexpress.utils.mapper.Mapper;
import br.edu.ifpb.decexpress.utils.mapper.aluno.AlunoMapperView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class TurmaMapperView implements Mapper<Turma, TurmaView> {
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private AlunoMapperView alunoMapperView;
    @Override
    public TurmaView map(Turma object) {
        List<Aluno>listaAlunos = alunoRepository.findAlunoByCodTurma(object.getCodTurma());

        return new TurmaView(
                object.getCodTurma(),
                object.getSerie(),
                object.getAno(),
                object.getNivel(),
                object.getTurma(),
                this.alunoMapperView.mapCollection(listaAlunos)
        );
    }

    @Override
    public List<TurmaView> mapCollection(Collection<Turma> objects) {
        List<TurmaView> turmaViews = new ArrayList<>();
        for (Turma turma : objects) {
            TurmaView turmaView = this.map(turma); // Use o método de mapeamento individual
            turmaViews.add(turmaView);
        }
        return turmaViews;
    }
}
