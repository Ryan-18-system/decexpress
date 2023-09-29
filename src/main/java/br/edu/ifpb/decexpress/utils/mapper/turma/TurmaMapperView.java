package br.edu.ifpb.decexpress.utils.mapper.turma;

import br.edu.ifpb.decexpress.model.entity.Turma;
import br.edu.ifpb.decexpress.model.use_case.manter_turma.dto.TurmaView;
import br.edu.ifpb.decexpress.utils.mapper.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class TurmaMapperView implements Mapper<Turma, TurmaView> {
    @Override
    public TurmaView map(Turma object) {
        return new TurmaView(
                object.getCodTurma(),
                object.getSerie(),
                object.getAno(),
                object.getNivel(),
                object.getTurma(),
                null
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
