package com.example.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.example.DTO.FotoRequestDTO;
import com.example.DTO.FotoResponseDTO;
import com.example.Model.Foto;
import com.example.Service.FotoService;

import java.net.URI;

@RestController
@RequestMapping("/fotos")
public class FotoController {
    
    private final FotoService service;

    public FotoController(FotoService service){
        this.service = service;
    }

    @GetMapping("/{id}")
    public FotoResponseDTO buscarPorId(@PathVariable Long id){
        Foto foto = service.buscarPorId(id);
        return toResponseDTO(foto);
    }

    @PostMapping
    public ResponseEntity<FotoResponseDTO> criar(@Valid @RequestBody FotoRequestDTO dto){
        Foto salvo = service.criar(dto);
        FotoResponseDTO responseDTO = toResponseDTO(salvo);
        var uri = URI.create("/fotos/" + salvo.getId()); 
        
        return ResponseEntity.created(uri).body(responseDTO);
    }

    @PutMapping("/{id}")
    public FotoResponseDTO atualizarFoto(@PathVariable Long id, @Valid @RequestBody FotoRequestDTO dto){
        Foto atualizada = service.atualizarFoto(id, dto);
        return toResponseDTO(atualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        service.deletarFoto(id);
        return ResponseEntity.noContent().build();
    }

    private FotoResponseDTO toResponseDTO(Foto foto) {
        FotoResponseDTO dto = new FotoResponseDTO();
        dto.setId(foto.getId());
        
        if (foto.getImovel() != null) {
            dto.setImovelId(foto.getImovel().getId()); 
        }
        
        dto.setLegenda(foto.getLegenda());
        dto.setUrl(foto.getUrl());
        dto.setPrincipal(foto.getPrincipal());
        return dto;
    }
}