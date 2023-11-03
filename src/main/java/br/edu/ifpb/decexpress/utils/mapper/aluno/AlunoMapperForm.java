package br.edu.ifpb.decexpress.utils.mapper.aluno;

import br.edu.ifpb.decexpress.model.entity.Aluno;
import br.edu.ifpb.decexpress.model.entity.Turma;
import br.edu.ifpb.decexpress.model.use_case.manter_aluno.dto.AlunoForm;
import br.edu.ifpb.decexpress.utils.mapper.Mapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Component
public class AlunoMapperForm implements Mapper<AlunoForm, Aluno> {
    @Override
    public Aluno map(AlunoForm object) {
        Aluno aluno = new Aluno();
        aluno.setNome(object.nome());
        aluno.setDataNascimento(this.convertToLocalDateToDate(object.dataNascimento()));
        aluno.setCpf(object.cpf());
        aluno.setRg(object.cpf());
        aluno.setMatricula(object.matricula());
        aluno.setTelefone(Long.valueOf(object.telefone()));
        aluno.setEmail(object.email());
        aluno.setEndereco(object.endereco());
        if (!Objects.isNull(object.codTurma())) {
            aluno.setCodTurma(new Turma(object.codTurma()));
        }
        return aluno;
    }

    public void mapAlteracao(AlunoForm dto, Aluno alunoBanco) {
        alunoBanco.setDataNascimento(this.convertToLocalDateToDate(dto.dataNascimento()));
        alunoBanco.setNome(dto.nome());
        alunoBanco.setTelefone(Long.valueOf(dto.telefone()));
        alunoBanco.setRg(dto.rg());
        if (!Objects.isNull(dto.codTurma())) {
            alunoBanco.setCodTurma(new Turma(dto.codTurma()));
        }
        alunoBanco.setEndereco(dto.endereco());
    }

    @Override
    public List<Aluno> mapCollection(Collection<AlunoForm> objects) {
        return null;
    }

    private Date convertToLocalDateToDate(LocalDate localDate) {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        return Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
    }

    private long gerarMatriculaAleatoria() {
        Random random = new Random();
        return 10000L + random.nextInt(90000);
    }

}
