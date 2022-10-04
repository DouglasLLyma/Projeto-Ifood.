package me.dio.sacola.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@NoArgsConstructor
@Builder
@Data
@Embeddable
@AllArgsConstructor

public class Endereco {
    private String cep;
    private String complemento;
}
