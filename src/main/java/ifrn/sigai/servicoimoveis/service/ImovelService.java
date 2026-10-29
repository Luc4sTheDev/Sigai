package ifrn.sigai.servicoimoveis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ifrn.sigai.servicoimoveis.dto.ImovelRequestDTO;
import ifrn.sigai.servicoimoveis.dto.ImovelResponseDTO;
import ifrn.sigai.servicoimoveis.exception.ImovelNaoEncontradoException;
import ifrn.sigai.servicoimoveis.model.Imovel;
import ifrn.sigai.servicoimoveis.repository.ImovelRepository;

@Service 
public class ImovelService {
    
    private final ImovelRepository repository;
    public ImovelService(ImovelRepository repository) {
        this.repository = repository;
    }
    public ImovelResponseDTO criar(ImovelRequestDTO dto) {
        Imovel salvo = repository.save(toEntity(dto));
        return toResponseDTO(salvo);
    }
    public List<ImovelResponseDTO> listar(String endereco, Double valorMaximo) {
        List<Imovel> imoveis;
        if (endereco != null && valorMaximo != null) {
            imoveis = repository.findByEnderecoContainingIgnoreCaseAndValorAluguelLessThanEqual(endereco, valorMaximo);
        } else if (endereco != null) {
            imoveis = repository.findByEnderecoContainingIgnoreCase(endereco);
        } else if (valorMaximo != null) {
            imoveis = repository.findByValorAluguelLessThanEqual(valorMaximo);
        } else {
            imoveis = repository.findAll();
        }

        return imoveis.stream().map(this::toResponseDTO).toList();
    }
    
    public ImovelResponseDTO buscarPorId(Long id) {
        Imovel imovel = repository.findById(id)
            .orElseThrow(() -> new ImovelNaoEncontradoException(id));
        return toResponseDTO(imovel);
    }
    public ImovelResponseDTO atualizar(Long id, ImovelRequestDTO dto) {
        Imovel imovel = repository.findById(id)
                .orElseThrow(() -> new ImovelNaoEncontradoException(id));

        imovel.setEndereco(dto.getEndereco());
        imovel.setValorAluguel(dto.getValorAluguel());
        imovel.setDescricao(dto.getDescricao());

        Imovel atualizado = repository.save(imovel);
        return toResponseDTO(atualizado);
    }
    public void deletar(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ImovelNaoEncontradoException(id));
        repository.deleteById(id);
    }
    private Imovel toEntity(ImovelRequestDTO dto) {
        return new Imovel(null, dto.getEndereco(), dto.getValorAluguel(), dto.getDescricao());
    }
    private ImovelResponseDTO toResponseDTO(Imovel imovel) {
        return new ImovelResponseDTO(imovel.getId(), imovel.getEndereco(), imovel.getValorAluguel());
    }
}
