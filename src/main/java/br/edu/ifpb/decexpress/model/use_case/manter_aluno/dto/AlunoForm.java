package br.edu.ifpb.decexpress.model.use_case.manter_aluno.dto;

import br.edu.ifpb.decexpress.model.entity.Endereco;
import br.edu.ifpb.decexpress.utils.LocalDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

public record AlunoForm(String nome,
                        @DateTimeFormat(pattern = "dd/MM/yyyy")
                        @JsonDeserialize(using = LocalDateDeserializer.class)
                        LocalDate dataNascimento,
                        String cpf,
                        String rg,
                        String telefone,
                        String email,
                        Endereco endereco,
                        Long codTurma) {
}
