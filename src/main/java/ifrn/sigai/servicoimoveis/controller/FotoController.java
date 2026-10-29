package ifrn.sigai.servicoimoveis.controller;

import java.net.URI;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ifrn.sigai.servicoimoveis.dto.FotoRequestDTO;
import ifrn.sigai.servicoimoveis.dto.FotoResponseDTO;
import ifrn.sigai.servicoimoveis.service.FotoService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/fotos")
public class FotoController {
    private final FotoService service;
    
    public FotoController(FotoService service) {
        this.service = service;
    }

    @GetMapping    
    public List<FotoResponseDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public FotoResponseDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<FotoResponseDTO> criar(@RequestBody FotoRequestDTO foto) {
        FotoResponseDTO entity = service.criar(foto);
        URI uri = URI.create("/fotos/" + entity.getId());
        
        return ResponseEntity.created(uri).body(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FotoResponseDTO> atualizar(@PathVariable Long id, @RequestBody FotoRequestDTO entity) {
        FotoResponseDTO atualizada = service.atualizar(id, entity);
        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();  
    }
}
