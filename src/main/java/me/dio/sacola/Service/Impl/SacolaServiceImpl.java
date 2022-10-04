package me.dio.sacola.Service.Impl;

import lombok.RequiredArgsConstructor;
import me.dio.sacola.Service.SacolaService;
import me.dio.sacola.enumeration.FormaPagamento;
import me.dio.sacola.model.Item;
import me.dio.sacola.model.Restaurante;
import me.dio.sacola.model.Sacola;
import me.dio.sacola.repository.ItemRepository;
import me.dio.sacola.repository.ProdutoRepository;
import me.dio.sacola.repository.SacolaRepository;
import me.dio.sacola.resource.dto.ItemDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SacolaServiceImpl implements SacolaService {
    private final SacolaRepository sacolaRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemRepository itemRepository;
    @Override
    public Item incluirItemNaSacola(ItemDto itemDto) {
        Sacola sacola = verSacola(itemDto.getProdutoId());

        if(sacola.isFechada()){
            throw new RuntimeException("Está Sacola está Fechada");
        }

        Item intemParaSerInserido = Item.builder()
                .quantidade(itemDto.getQuantidade())
                .sacola(sacola)
                .produto(produtoRepository.findById(itemDto.getProdutoId()).orElseThrow(
                        () -> {
                            throw  new RuntimeException("Esse Produto Não Existe.");
                        }
                ))
                .build();

        List<Item> itensDaSacola =  sacola.getItens();
        if(itensDaSacola.isEmpty()) {
            itensDaSacola.add(intemParaSerInserido);
        } else {
            Restaurante restauranteAtual =  itensDaSacola.get(0).getProduto().getRestaurante();
            Restaurante restauranteDoItemParaAdicionar =  intemParaSerInserido.getProduto().getRestaurante();
        if(restauranteAtual.equals(restauranteDoItemParaAdicionar)) {
            itensDaSacola.add(intemParaSerInserido);
            } else {
            throw  new RuntimeException("Não é Possível Adicionar Produtos de Restaurantes Diferentes.");
            }
        }


        sacolaRepository.save(sacola);
        return itemRepository.save(intemParaSerInserido);
    }
     @Override
    public Sacola verSacola(Long id)
     {
        return sacolaRepository.findById(id).orElseThrow(
                () -> {
                    throw  new RuntimeException("Está Sacola Não Existe.");
                }
        );
    }

    @Override
    public Sacola fecharSacola(Long id, int numeroformaPagamento) {
        Sacola sacola = verSacola(id);

        if (sacola.getItens().isEmpty()){
            throw  new RuntimeException("Inclua Itens na Sacola.");
        }

        FormaPagamento formaPagamento =
            numeroformaPagamento == 0 ? FormaPagamento.DINHEIRO : FormaPagamento.MAQUININHA;
        sacola.setFormaPagamento(formaPagamento);
        sacola.setFechada(true);
        sacolaRepository.save(sacola);
        return sacolaRepository.save(sacola);

    }
}
