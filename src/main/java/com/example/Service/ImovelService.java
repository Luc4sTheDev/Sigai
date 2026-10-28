package com.example.Service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.DTO.ImovelRequestDTO;
import com.example.DTO.ImovelResponseDTO;
import com.example.Exception.ImovelNaoEncontradoException;
import com.example.Model.Imovel;
import com.example.Repository.ImovelRepository;

@Service 
public class ImovelService {
    private final ImovelRepository repository;

    public ImovelService(ImovelRepository repository){
        this.repository = repository;
    }

    public List<Imovel> listar() {
        return repository.findAll();
    }
    
    public ImovelResponseDTO buscarPorId(Long id) {
        Imovel imovel = repository.findById(id)
                .orElseThrow(() -> new ImovelNaoEncontradoException(id));
        return toResponseDTO(imovel);
    }

    public ImovelResponseDTO criar(ImovelRequestDTO dto) {
    Imovel salvo = repository.save(toEntity(dto));
    return toResponseDTO(salvo);
}

    private Imovel toEntity(ImovelRequestDTO dto) {
        Imovel imovel = new Imovel();
        imovel.setDescricao(dto.getDescricao());
        imovel.setEndereco(dto.getEndereco());
        imovel.setValorAluguel(dto.getValorAluguel());
        return imovel;
    }

    private ImovelResponseDTO toResponseDTO(Imovel imovel) {
        ImovelResponseDTO dto = new ImovelResponseDTO();
        dto.setId(imovel.getId());
        dto.setDescricao(imovel.getDescricao());
        dto.setEndereco(imovel.getEndereco());
        dto.setValorAluguel(imovel.getValorAluguel());
        return dto;
    }

    public Imovel atualizar(Long id, Imovel novo){
        return repository.findById(id).map(existente -> {
            novo.setId(id);
            return repository.save(novo);
        }).orElseThrow(() -> new ImovelNaoEncontradoException(id));
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }
}