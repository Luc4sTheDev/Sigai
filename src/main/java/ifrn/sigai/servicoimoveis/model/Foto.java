package ifrn.sigai.servicoimoveis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "fotos")
@Data 
public class Foto {

    public Foto() {
        super();
    }

    public Foto(Long id, Imovel imovel, String url, String legenda, Boolean principal) {
        this.id = id;
        this.imovel = imovel;
        this.url = url;
        this.legenda = legenda;
        this.principal = principal;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imovel_id", nullable = false)
    private Imovel imovel;
    
    private String url;
    private String legenda;
    private Boolean principal = false;

}
