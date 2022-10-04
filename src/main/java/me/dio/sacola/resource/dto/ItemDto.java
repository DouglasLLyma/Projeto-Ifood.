package me.dio.sacola.resource.dto;

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

public class ItemDto {
    private Long produtoId;
    private int quantidade;
    private Long idSacola;
}
