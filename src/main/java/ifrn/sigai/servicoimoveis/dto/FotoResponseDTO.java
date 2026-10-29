package ifrn.sigai.servicoimoveis.dto;

import lombok.Data;

@Data  
public class FotoResponseDTO {

    public FotoResponseDTO() {
        super();
    }
    public FotoResponseDTO(Long id, Long imovelId, String url, String legenda, Boolean principal) {
        this.id = id;
        this.imovelId = imovelId;
        this.url = url;
        this.legenda = legenda;
        this.principal = principal;
    }

    private Long id;
    private Long imovelId;
    private String url;
    private String legenda;
    private Boolean principal;
}