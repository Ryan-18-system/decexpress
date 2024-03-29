package br.edu.ifpb.decexpress.utils.mapper.turma;

import br.edu.ifpb.decexpress.model.entity.Turma;
import br.edu.ifpb.decexpress.model.use_case.manter_turma.dto.TurmaForm;
import br.edu.ifpb.decexpress.utils.GlobalConstantes;
import br.edu.ifpb.decexpress.utils.mapper.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Component
public class TurmaMapperForm implements Mapper<TurmaForm, Turma> {
    @Override
    public Turma map(TurmaForm object) {
        Turma turma = new Turma();
        turma.setSerie(object.serie());
        turma.setAno(object.ano());
        turma.setNivel(object.nivel());
        turma.setTurma(object.turma());
        turma.setStRegistro(GlobalConstantes.ST_ATIVO);

        return turma;
    }
    public TurmaForm mapInverso(Turma turma){
        return TurmaForm.builder()
                .turma(turma.getTurma())
                .ano(turma.getAno())
                .nivel(turma.getNivel())
                .serie(turma.getSerie())
                .build();
    }

    @Override
    public List<Turma> mapCollection(Collection<TurmaForm> objects) {
        return null;
    }
}
