package com.example.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.example.DTO.ImovelRequestDTO;
import com.example.DTO.ImovelResponseDTO;
import com.example.Service.ImovelService;

import java.net.URI;
import java.util.List;

@RestController 
@RequestMapping("/imoveis")
public class ImovelController {

    private final ImovelService service;

    public ImovelController(ImovelService service){
        this.service = service;
    }

    @GetMapping 
    public List<ImovelResponseDTO> listar(){
        return service.listar();
    }

    @GetMapping("/{id}")
    public ImovelResponseDTO buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id);
    }
   
    @PostMapping 
    public ResponseEntity<ImovelResponseDTO> criar(@Valid @RequestBody ImovelRequestDTO dto){
        ImovelResponseDTO responseDTO = service.criar(dto);
        var uri = URI.create("/imoveis/" + responseDTO.getId());
        return ResponseEntity.created(uri).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ImovelResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody ImovelRequestDTO dto){
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}