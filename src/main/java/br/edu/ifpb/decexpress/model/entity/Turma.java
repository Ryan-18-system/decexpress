package br.edu.ifpb.decexpress.model.entity;

import br.edu.ifpb.decexpress.utils.formatter.StringUpperCaseListener;
import br.edu.ifpb.decexpress.utils.nivelTurma;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "tb_turma")
@Data
@EqualsAndHashCode(of = "codTurma")
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners({StringUpperCaseListener.class})
public class Turma implements Serializable {

    @Serial
    private static final long serialVersionUID = -7453119406203492598L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_turma")
    private Long codTurma;
    private Integer serie;
    private Integer ano;
    @Enumerated(EnumType.STRING)
    private nivelTurma nivel;
    @Column(length = 1, columnDefinition = "CHAR(1)")
    private char turma;
    @Column(name = "st_registro")
    private Integer stRegistro;
}
