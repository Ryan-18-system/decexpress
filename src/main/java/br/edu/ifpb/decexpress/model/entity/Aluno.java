package br.edu.ifpb.decexpress.model.entity;

import br.edu.ifpb.decexpress.utils.GlobalConstantes;
import br.edu.ifpb.decexpress.utils.formatter.StringUpperCaseListener;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "tb_aluno")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "codAluno")
@EntityListeners({StringUpperCaseListener.class})
public class Aluno implements Serializable {

    @Serial
    private static final long serialVersionUID = -720148292547755164L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_aluno")
    private Long codAluno;
    @Column(length = 200)
    private String nome;
    @Column(name = "data_nascimento")
    @Past
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataNascimento;
    @Column(length = 11)
    private String cpf;
    @Column(length = 15)
    private String rg;
    @Column(name = "matricula")
    private Long matricula;
    @Column
    private Long telefone;
    @Column(length = 150)
    private String email;
    @Embedded
    private Endereco endereco;
    @ManyToOne()
    @JoinColumn(name = "cod_turma", referencedColumnName = "cod_turma")
    private Turma codTurma;
    @Column(name = "st_registro")
    private Integer stRegistro;

    @PrePersist
    public void stSituacao() {
        this.stRegistro = GlobalConstantes.ST_ATIVO;
    }
}
