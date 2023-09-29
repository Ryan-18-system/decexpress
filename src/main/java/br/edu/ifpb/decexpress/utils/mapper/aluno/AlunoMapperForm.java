package br.edu.ifpb.decexpress.utils.mapper.aluno;

import br.edu.ifpb.decexpress.model.entity.Aluno;
import br.edu.ifpb.decexpress.model.use_case.manter_aluno.dto.AlunoForm;
import br.edu.ifpb.decexpress.utils.mapper.Mapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class AlunoMapperForm implements Mapper<AlunoForm, Aluno> {
    @Override
    public Aluno map(AlunoForm object) {
        Aluno aluno = new Aluno();
        aluno.setNome(object.nome());
        aluno.setDataNascimento(object.dataNascimento());
        aluno.setCpf(object.cpf());
        aluno.setRg(object.cpf());
        aluno.setMatricula(object.matricula());
        aluno.setTelefone(Long.valueOf(object.telefone()));
        aluno.setEmail(object.email());
        aluno.setEndereco(object.endereco());

        return aluno;
    }

    @Override
    public List<Aluno> mapCollection(Collection<AlunoForm> objects) {
        return null;
    }
}
