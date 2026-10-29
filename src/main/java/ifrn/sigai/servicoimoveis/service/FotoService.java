package ifrn.sigai.servicoimoveis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ifrn.sigai.servicoimoveis.dto.FotoRequestDTO;
import ifrn.sigai.servicoimoveis.dto.FotoResponseDTO;
import ifrn.sigai.servicoimoveis.exception.FotoNaoEncontradaException;
import ifrn.sigai.servicoimoveis.exception.ImovelNaoEncontradoException;
import ifrn.sigai.servicoimoveis.model.Foto;
import ifrn.sigai.servicoimoveis.model.Imovel;
import ifrn.sigai.servicoimoveis.repository.FotoRepository;
import ifrn.sigai.servicoimoveis.repository.ImovelRepository;

@Service 
public class FotoService {

    private final FotoRepository repository;
    private final ImovelRepository imovelRepository;

    public FotoService(FotoRepository repository, ImovelRepository imovelRepository) {
        this.repository = repository;
        this.imovelRepository = imovelRepository;
    }
    public FotoResponseDTO criar(FotoRequestDTO dto) {
        Foto salvo = repository.save(toEntity(dto));
        return toResponseDTO(salvo);
    }
    public FotoResponseDTO buscarPorId(Long id) {
        Foto foto = repository.findById(id)
            .orElseThrow(() -> new FotoNaoEncontradaException(id));
        return toResponseDTO(foto);
    }
    public FotoResponseDTO atualizar(Long id, FotoRequestDTO dto) {
        repository.findById(id)
                .orElseThrow(() -> new FotoNaoEncontradaException(id));

        Foto fotoAtualizada = toEntity(dto);
        fotoAtualizada.setId(id);
        fotoAtualizada = repository.save(fotoAtualizada); 

        return toResponseDTO(fotoAtualizada);
    }
    public void deletar(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new FotoNaoEncontradaException(id));
        repository.deleteById(id);
    }
    public List<FotoResponseDTO> listar() {
        List<Foto> fotos = repository.findAll();
        return fotos.stream()
                .map(this::toResponseDTO)
                .toList();
    }


    private Foto toEntity(FotoRequestDTO dto) {
        Imovel imovel = imovelRepository.findById(dto.getImovelId())
            .orElseThrow(() -> new ImovelNaoEncontradoException(dto.getImovelId()));
        return new Foto(null,imovel, dto.getUrl(), dto.getLegenda(), dto.getPrincipal());
    }
    private FotoResponseDTO toResponseDTO(Foto foto) {
        Imovel imovel = foto.getImovel();
        long idimovel = imovel.getId();
        return new FotoResponseDTO(foto.getId(), idimovel, foto.getUrl(), foto.getLegenda(), foto.getPrincipal());
        //return new FotoResponseDTO(foto.getId(), foto.getImovel().getId(), foto.getUrl(), foto.getLegenda(), foto.getPrincipal());
    }
}
