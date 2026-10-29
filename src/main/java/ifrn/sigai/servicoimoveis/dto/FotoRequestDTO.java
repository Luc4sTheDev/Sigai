package ifrn.sigai.servicoimoveis.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data 
public class FotoRequestDTO {

    public FotoRequestDTO() {
    }   
    
    public FotoRequestDTO(Long imovelId, String url, String legenda, Boolean principal) {
        this.imovelId = imovelId;
        this.url = url;
        this.legenda = legenda;
        this.principal = principal;
    }

    @NotNull(message = "O ID do imóvel não pode ser nulo ou vazio.")
    private Long imovelId;

    @NotBlank(message = "A URL da foto não pode ser nula ou vazia.")
    private String url;
    
    @Size(max = 200, message = "A legenda não pode ter mais de 200 caracteres.")
    private String legenda;
    private Boolean principal;
}
