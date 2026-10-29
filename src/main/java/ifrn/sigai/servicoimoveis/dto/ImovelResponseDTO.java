package ifrn.sigai.servicoimoveis.dto;

import lombok.Data;

@Data  
public class ImovelResponseDTO {

    public ImovelResponseDTO() {
    }   
    public ImovelResponseDTO(Long id, String endereco, Double valorAluguel) {
        this.id = id;
        this.endereco = endereco;
        this.valorAluguel = valorAluguel;
    }

    private Long id;
    private String endereco;
    private Double valorAluguel;
}
