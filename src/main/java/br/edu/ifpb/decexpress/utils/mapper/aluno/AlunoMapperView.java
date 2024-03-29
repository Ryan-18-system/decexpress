package br.edu.ifpb.decexpress.utils.mapper.aluno;

import br.edu.ifpb.decexpress.model.entity.Aluno;
import br.edu.ifpb.decexpress.model.use_case.manter_aluno.dto.AlunoForm;
import br.edu.ifpb.decexpress.model.use_case.manter_aluno.dto.AlunoView;
import br.edu.ifpb.decexpress.utils.mapper.Mapper;
import br.edu.ifpb.decexpress.utils.mapper.turma.TurmaMapperForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AlunoMapperView implements Mapper<Aluno, AlunoView> {


    @Autowired
    private TurmaMapperForm turmaMapperForm;
    @Override
    public AlunoView map(Aluno object) {
        AlunoView retorno = new AlunoView();
        retorno.setCpf(object.getCpf());
        retorno.setCodAluno(object.getCodAluno());
        retorno.setDataNascimento(new Date(object.getDataNascimento().getTime()));
        retorno.setNome(object.getNome());
        retorno.setRg(object.getRg());
        retorno.setMatricula(object.getMatricula());
        retorno.setTelefone(object.getTelefone().toString());
        retorno.setEmail(object.getEmail());
        if(!Objects.isNull(object.getCodTurma())){
           retorno.setTurma(this.turmaMapperForm.mapInverso(object.getCodTurma()));
        }
        retorno.setEndereco(object.getEndereco());

        return retorno;
    }

    @Override
    public List<AlunoView> mapCollection(Collection<Aluno> objects) {
        List<AlunoView> alunoViews = new ArrayList<>();

        for (Aluno aluno : objects) {
            AlunoView alunoView = new AlunoView();
            alunoView.setDataNascimento(new Date(aluno.getDataNascimento().getTime()));
            alunoView.setCpf(aluno.getCpf());
            alunoView.setCodAluno(aluno.getCodAluno());
            alunoView.setNome(aluno.getNome());
            alunoView.setRg(aluno.getRg());
            alunoView.setMatricula(aluno.getMatricula());
            alunoView.setTelefone(aluno.getTelefone().toString());
            alunoView.setEmail(aluno.getEmail());
            alunoView.setEndereco(aluno.getEndereco());

            alunoViews.add(alunoView);
        }

        return alunoViews;
    }
}
