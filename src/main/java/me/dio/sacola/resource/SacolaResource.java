package me.dio.sacola.resource;

import lombok.RequiredArgsConstructor;
import me.dio.sacola.Service.SacolaService;
import me.dio.sacola.model.Item;
import me.dio.sacola.model.Sacola;
import me.dio.sacola.resource.dto.ItemDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ifood-deweek/sacolas/id")
@RequiredArgsConstructor
public class SacolaResource {
    private final SacolaService sacolaService;


    @PostMapping
    public Item incluirItemNaSacola(@RequestBody ItemDto itemDto){
        return sacolaService.incluirItemNaSacola(itemDto);
    }

    @GetMapping("/{id}")
    public Sacola verSacola(@PathVariable("id") Long id){
        return sacolaService.verSacola(id);

    }

    @PatchMapping("/fecharSacola/{sacolaId}")
    public Sacola fecharSacola(Long sacolaId,@RequestParam("formaPagamento") int formaPagamento ){
        return sacolaService.fecharSacola(sacolaId, formaPagamento);
    }
}
