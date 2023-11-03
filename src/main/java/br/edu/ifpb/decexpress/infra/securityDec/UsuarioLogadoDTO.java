package br.edu.ifpb.decexpress.infra.securityDec;

import lombok.*;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UsuarioLogadoDTO {
    private boolean isAdmin;
    private String name;
    private Long matricula;
    private Integer user_id;
}
