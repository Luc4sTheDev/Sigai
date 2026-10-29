package com.example.Service;

import org.springframework.stereotype.Service;

import com.example.DTO.FotoRequestDTO;
import com.example.Exception.FotoNaoEncontrada;
import com.example.Exception.ImovelNaoEncontradoException;
import com.example.Model.Foto;
import com.example.Model.Imovel;
import com.example.Repository.FotoRepository;
import com.example.Repository.ImovelRepository;

@Service 
public class FotoService {
    
    private final FotoRepository fotoRepository;
    private final ImovelRepository imovelRepository; 

    // 2. Injetando ambos os repositórios no construtor
    public FotoService(FotoRepository fotoRepository, ImovelRepository imovelRepository){
        this.fotoRepository = fotoRepository;
        this.imovelRepository = imovelRepository;
    }

    public Foto criar(FotoRequestDTO fotoDto){
        Foto foto = toEntity(fotoDto);
        return fotoRepository.save(foto);
    }

    public Foto buscarPorId(Long id){
        return fotoRepository.findById(id)
                .orElseThrow(() -> new FotoNaoEncontrada(id));
    }

    public Foto atualizarFoto(Long id, FotoRequestDTO dto){
        Foto fotoExistente = fotoRepository.findById(id)
                .orElseThrow(() -> new FotoNaoEncontrada(id));


        fotoExistente.setUrl(dto.getUrl());
        fotoExistente.setLegenda(dto.getLegenda());
        fotoExistente.setPrincipal(dto.getPrincipal());

        return fotoRepository.save(fotoExistente);
    }

    public void deletarFoto(Long id){
        fotoRepository.deleteById(id);
    }
    
    private Foto toEntity(FotoRequestDTO dto) {
        Imovel imovel = imovelRepository.findById(dto.getImovelId())
                .orElseThrow(() -> new ImovelNaoEncontradoException(dto.getImovelId()));
                
        Foto foto = new Foto();
        foto.setImovel(imovel); 
        foto.setUrl(dto.getUrl());
        foto.setLegenda(dto.getLegenda());
        foto.setPrincipal(dto.getPrincipal());
        
        return foto;
    }
}