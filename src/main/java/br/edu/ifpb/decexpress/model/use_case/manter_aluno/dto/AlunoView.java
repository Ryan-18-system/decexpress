package br.edu.ifpb.decexpress.model.use_case.manter_aluno.dto;

import br.edu.ifpb.decexpress.model.entity.Endereco;
import br.edu.ifpb.decexpress.model.use_case.manter_turma.dto.TurmaForm;
import br.edu.ifpb.decexpress.utils.formatter.FormatarCampo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoView implements Serializable {

    private Long codAluno;
    private String nome;
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT")
    private Date dataNascimento;
    @FormatarCampo(mascara = "cpf")
    private String cpf;
    private String rg;
    private Long matricula;
    @FormatarCampo(mascara = "telefone")
    private String telefone;
    private String email;
    private Endereco endereco;
    private TurmaForm turma;
}
