package br.edu.ifpb.decexpress.model.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Endereco {

    private Long cep;
    private String logradouro;
    private String bairro;
    private String numero;
    private String uf;
    private String municipio;
    private String complemento;
}
