package br.edu.ifpb.decexpress.model.use_case.manter_aluno.dto;

import br.edu.ifpb.decexpress.model.entity.Endereco;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public record AlunoForm(String nome,
                        @DateTimeFormat(pattern = "dd/MM/yyyy")
                        Date dataNascimento,
                        String cpf,
                        String rg,
                        Long matricula,
                        String telefone,
                        String email,
                        Endereco endereco,
                        Long codTurma) {
}
